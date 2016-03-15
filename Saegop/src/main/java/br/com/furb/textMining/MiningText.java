package br.com.furb.textMining;

import java.net.SocketTimeoutException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.maps.GeoApiContext;

public class MiningText {
	
	ArrayList<String> listaString = new ArrayList<String>();
	ArrayList<AnalisarInformacao> listaAnalisar = new ArrayList<AnalisarInformacao>();
	private static final int qtPages = 31;
	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyClyg2c5hxqJotZHUhAPx8oufyvgzlaix4");

	public void extrair() {
		try {
			Document document = Jsoup
					.connect(
							"http://www.saladenoticias.net/?s=atividade%20operacionais&submit=Pesquisar")
					.timeout(10 * 1000).get();
			Elements searchResults = document.select(".post > h2 > a");
			for (Element result : searchResults) {
				String link = result.attr("href");
				listaString.add(link);
			}

			for (int i = 2; i <= qtPages; i++) {
				Document documentSub = Jsoup
						.connect(
								"http://www.saladenoticias.net/?s=atividade+operacionais&submit=Pesquisar&paged="
										+ i).timeout(10 * 1000).get();
				Elements searchResultsSub = documentSub
						.select(".post > h2 > a");
				for (Element resultSub : searchResultsSub) {
					String link = resultSub.attr("href");
					listaString.add(link);
					System.out.println(link);
				}
			}

		} catch (SocketTimeoutException s) {
			System.out.println(s.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			System.out.println("QUANTIDADE DE LINKS " + listaString.size());
		}

		try {
			for (String dsLink : listaString) {
				extraiarOcorrencias(dsLink);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("QUANTIDADES DE FATOS EXTRAÍDOS "
					+ listaAnalisar.size());
		}

		try {

			for (AnalisarInformacao ocorrencias : listaAnalisar) {
				ocorrencias.processar();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();

		}

	}

	private void extraiarOcorrencias(String dsLink) throws Exception {
		Document document = Jsoup.connect(dsLink).timeout(10 * 1000).get();
		Elements searchResults = document.select(".post-entry > p");
		Elements searchDtOcorrencoa = document.select(".heading-date");
		String dsData = searchDtOcorrencoa.text();

		String dsHorario = "";
		String dsLocal = "";
		String dsFato = "";
		System.out.println(dsLink);
		for (Element result : searchResults) {

			if (result.text().trim().equalsIgnoreCase(" ")) {
				continue;
			}

			if (result.text().equalsIgnoreCase("Ocorrências de destaque:")) {
				continue;
			}

			if (result.text().equalsIgnoreCase(". Ocorrências de destaque:")) {
				continue;
			}

			if (result.text().contentEquals("UTILIDADE PÚBLICA")) {
				break;
			}

			if ((result.text().length() > 17)
					&& (result.text().trim().substring(0, 17)
							.equalsIgnoreCase("UTILIDADE PÚBLICA"))) {
				break;
			}

			if ((result.text().length() > 19)
					&& (result.text().trim().substring(0, 19)
							.equalsIgnoreCase("  UTILIDADE PÚBLICA"))) {
				break;
			}

			if ((result.text().length() > 8)
					&& (result.text().substring(0, 8)
							.equalsIgnoreCase("Horário:"))) {

				if (result.text().indexOf("Local:") > 0) {
					dsHorario = result.text().substring(0,
							result.text().indexOf("Local:"));
				} else {
					dsHorario = result.text();
				}

				if (result.text().indexOf("Local:") > 0) {

					int qtFato = result.text().indexOf("Fato:");

					if (qtFato <= 0) {
						qtFato = result.text().length();
					}

					dsLocal = result.text().substring(
							result.text().indexOf("Local:"), qtFato);
				}

				if (result.text().indexOf("Fato:") > 0) {
					dsFato = result.text().substring(
							result.text().indexOf("Fato:"),
							result.text().length());
				}

			}

			if ((dsLocal.equalsIgnoreCase(""))
					&& (result.text().length() > 6)
					&& (result.text().substring(0, 6)
							.equalsIgnoreCase("Local:"))) {
				dsLocal = result.text();
			}

			if ((dsFato.equalsIgnoreCase(""))
					&& (result.text().length() > 5)
					&& (result.text().substring(0, 5).equalsIgnoreCase("Fato:"))) {
				dsFato = result.text();
			}

			if (getTodosCamposPreenchidos(dsHorario, dsLocal, dsFato)) {
				AnalisarInformacao ocorrenciasPoliciais = new AnalisarInformacao(
						dsFato, dsLocal,context);
				listaAnalisar.add(ocorrenciasPoliciais);
				dsHorario = "";
				dsLocal = "";
				dsFato = "";
			}
		}

	}

	public boolean getTodosCamposPreenchidos(String dsHorario, String dsLocal,
			String dsFato) {
		return !dsHorario.equalsIgnoreCase("") && !dsLocal.equalsIgnoreCase("")
				&& !dsFato.equalsIgnoreCase("");
	}

}

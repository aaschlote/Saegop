package br.com.furb.textMining;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;
import br.com.furb.controller.AtividadePolicialController;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;

import com.google.maps.GeoApiContext;

public class MiningText {
	
	private ArrayList<String> listaLinks = new ArrayList<String>();
	private ArrayList<AnalisarInformacao> listaAnalisar = new ArrayList<AnalisarInformacao>();
	private ArrayList<AtividadePolicial> listaAtividades = new ArrayList<AtividadePolicial>();
	private static final int qtPages = 90;
	private GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyClyg2c5hxqJotZHUhAPx8oufyvgzlaix4");
	private ConnectionDB conection = new ConnectionDB();
	Stemmer stemmer;
	private AtividadePolicialController atividadePolicController = new AtividadePolicialController(conection);

	public void extrair() {
		try {
			
			stemmer = new OrengoStemmer();
			
			removeAllInstances();
			
				Document document = Jsoup
						.connect(
								"http://www.saladenoticias.net/?s=REGI%C3%83O+POLICIAL+MILITAR&submit=Pesquisar")
						.timeout(10 * 1000).get();
				Elements searchResults = document.select(".post > h2 > a");
				for (Element result : searchResults) {
					String link = result.attr("href");
					listaLinks.add(link);
				}
			
			document = null;
			
			searchResults.clear();

			for (int i = 2; i <= qtPages; i++) {
				Document documentSub = Jsoup
						.connect(
								"http://www.saladenoticias.net/?s=REGI%C3%83O+POLICIAL+MILITAR&submit=Pesquisar&paged="
										+ i).timeout(10 * 1000).get();
				Elements searchResultsSub = documentSub
						.select(".post > h2 > a");
				for (Element resultSub : searchResultsSub) {
					String link = resultSub.attr("href");
					listaLinks.add(link);
					System.out.println(link);
				}
				
				documentSub = null;
				searchResultsSub.clear();
			}

		} catch (SocketTimeoutException s) {
			System.out.println(s.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			System.out.println("QUANTIDADE DE LINKS " + listaLinks.size());
		}

		try {
			for (String dsLink : listaLinks) {
				extraiarOcorrencias(dsLink);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			System.out.println("QUANTIDADES DE FATOS EXTRAÍDOS "
					+ listaAnalisar.size());
		}
		
		listaLinks.clear();

		try {

			for (AnalisarInformacao ocorrencias : listaAnalisar) {
				ocorrencias.processar();
				
				AtividadePolicial atividade = new AtividadePolicial();
				atividade.setDsBairro(ocorrencias.getDsBairro());
				atividade.setDsEndereco(ocorrencias.getDsLocal());
				atividade.setLatitude(ocorrencias.getLatitude());
				atividade.setLongitude(ocorrencias.getLongitude());
				atividade.setDsFato(ocorrencias.getDsFato());
				Calendar dtOcorrencia = Calendar.getInstance();
				dtOcorrencia.setTime(ocorrencias.getDtOcorrencia());
				atividade.setDtOcorrencia(dtOcorrencia);				
				listaAtividades.add(atividade);
				
				System.out.println("QUANTIDADE DE ATIVIDADES " +  listaAtividades.size());
			}
			
			System.out.println("Inserindo");
			atividadePolicController.inserir(listaAtividades);
			
			System.out.println("fim");

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

		System.out.println(dsLink);
		for (Element result : searchResults) {

			if	(result.text().trim().equalsIgnoreCase(" ")){
				continue;
			}
			
			if	(result.text().equalsIgnoreCase("Ocorrências de destaque:")){
				continue;
			}
			
			if	(result.text().equalsIgnoreCase(". Ocorrências de destaque:")){
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
							.equalsIgnoreCase("  UTILIDADE PÚBLICA"))) {
				break;
			}

			extrairFatos(dsData,result.text());
		}
		
		searchResults.clear();
		searchDtOcorrencoa.clear();
		document = null;
	}
	
	private void extrairFatos(String dsData, String dsTexto){
		
		String dsHorario = "";
		String dsLocal = "";
		String dsFato = "";
		
		if ((dsTexto.length() > 8)
				&& (dsTexto.substring(0, 8)
						.equalsIgnoreCase("Horário:"))) {

			if (dsTexto.indexOf("Local:") > 0) {
				dsHorario = dsTexto.substring(0,
						dsTexto.indexOf("Local:"));
			} else {
				dsHorario = dsTexto;
			}

			if (dsTexto.indexOf("Local:") > 0) {

				int qtFato = dsTexto.indexOf("Fato:");

				if (qtFato <= 0) {
					qtFato = dsTexto.length();
				}

				dsLocal = dsTexto.substring(
						dsTexto.indexOf("Local:"), qtFato);
			}

			if (dsTexto.indexOf("Fato:") > 0) {
				dsFato = dsTexto.substring(
						dsTexto.indexOf("Fato:"),
						dsTexto.length());
			}

		}

		if ((dsLocal.equalsIgnoreCase(""))
				&& (dsTexto.length() > 6)
				&& (dsTexto.substring(0, 6)
						.equalsIgnoreCase("Local:"))) {
			dsLocal = dsTexto;
		}

		if ((dsFato.equalsIgnoreCase(""))
				&& (dsTexto.length() > 5)
				&& (dsTexto.substring(0, 5).equalsIgnoreCase("Fato:"))) {
			dsFato = dsTexto;
		}

		if (getTodosCamposPreenchidos(dsHorario, dsLocal, dsFato)) {
			
			int qtHorario = dsFato.indexOf("Horário:");

			if	(qtHorario > 0){
				String dsNovoTexto = dsFato.substring(qtHorario,dsFato.length());
				
				if	(dsNovoTexto.length() > 10){
					extrairFatos(dsData,dsNovoTexto);
				}
				
				dsFato = dsFato.substring(0,qtHorario-8);
			}
			
			if	(dsFato.indexOf("UTILIDADE PÚBLICA") > 0){
				dsFato = dsFato.substring(0,dsFato.indexOf("UTILIDADE PÚBLICA")-17);
			}
			
			if	(dsFato.length() > 4000){
				dsFato = dsFato.substring(0,3999);
			}
			
			AnalisarInformacao ocorrenciasPoliciais = new AnalisarInformacao(
					dsFato, dsLocal,dsData,context, stemmer);
			listaAnalisar.add(ocorrenciasPoliciais);
			dsHorario = "";
			dsLocal = "";
			dsFato = "";
		}
		
	}

	public boolean getTodosCamposPreenchidos(String dsHorario, String dsLocal,
			String dsFato) {
		return !dsHorario.equalsIgnoreCase("") && !dsLocal.equalsIgnoreCase("")
				&& !dsFato.equalsIgnoreCase("");
	}
	
	public final void removeAllInstances() {
		atividadePolicController.deletarAtividades();
	}

}

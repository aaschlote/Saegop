package br.com.furb.textMining;

import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.persistence.Query;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import br.com.furb.controller.CriarAtividadePolicial;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;

import com.google.maps.GeoApiContext;

public class MiningText {
	
	ArrayList<String> listaString = new ArrayList<String>();
	ArrayList<AnalisarInformacao> listaAnalisar = new ArrayList<AnalisarInformacao>();
	ArrayList<AtividadePolicial> listaAtividades = new ArrayList<AtividadePolicial>();
	private static final int qtPages = 41;
	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyClyg2c5hxqJotZHUhAPx8oufyvgzlaix4");
	ConnectionDB conection = new ConnectionDB();

	public void extrair() {
		try {
			
			removeAllInstances();
			
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
			System.out.println("QUANTIDADES DE FATOS EXTRA�DOS "
					+ listaAnalisar.size());
		}

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
			}
			
			CriarAtividadePolicial criarAtividade = new CriarAtividadePolicial(listaAtividades, conection);
			criarAtividade.inserir();

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

			if (result.text().trim().equalsIgnoreCase("�")) {
				continue;
			}

			if (result.text().equalsIgnoreCase("Ocorr�ncias de destaque:")) {
				continue;
			}

			if (result.text().equalsIgnoreCase(". Ocorr�ncias de destaque:")) {
				continue;
			}

			if (result.text().contentEquals("UTILIDADE P�BLICA")) {
				break;
			}

			if ((result.text().length() > 17)
					&& (result.text().trim().substring(0, 17)
							.equalsIgnoreCase("UTILIDADE P�BLICA"))) {
				break;
			}

			if ((result.text().length() > 19)
					&& (result.text().trim().substring(0, 19)
							.equalsIgnoreCase("� UTILIDADE P�BLICA"))) {
				break;
			}

			extrairFatos(dsData,result.text());
		}
	}
	
	public void extrairFatos(String dsData, String dsTexto){
		
		String dsHorario = "";
		String dsLocal = "";
		String dsFato = "";
		
		if ((dsTexto.length() > 8)
				&& (dsTexto.substring(0, 8)
						.equalsIgnoreCase("Hor�rio:"))) {

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
			
			int qtHorario = dsFato.indexOf("Hor�rio:");

			if	(qtHorario > 0){
				String dsNovoTexto = dsFato.substring(qtHorario,dsFato.length());
				
				if	(dsNovoTexto.length() > 10){
					extrairFatos(dsData,dsNovoTexto);
				}
				
				dsFato = dsFato.substring(0,qtHorario-8);
			}
			
			
			if	(dsFato.indexOf("UTILIDADE P�BLICA") > 0){
				dsFato = dsFato.substring(0,dsFato.indexOf("UTILIDADE P�BLICA")-17);
			}
			
			
			
			if	(dsFato.length() > 4000){
				dsFato = dsFato.substring(0,3999);
			}
			
			
			AnalisarInformacao ocorrenciasPoliciais = new AnalisarInformacao(
					dsFato, dsLocal,dsData,context);
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
		conection.getManager().getTransaction().begin();
	    Query query = conection.getManager().createQuery("from AtividadePolicial as a");
	    for (Object obj : query.getResultList()) {
	    	conection.getManager().remove(obj);
	    }
	    conection.getManager().getTransaction().commit();
	}

}

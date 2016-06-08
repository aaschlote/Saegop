package br.com.furb.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestColetaDado {
	
	public static void main(String[] args) {
		
		try {
			
			Document document = Jsoup.connect("http://www.saladenoticias.net/?p=189941").timeout(10 * 1000).get();
			Elements searchResults = document.select(".post-entry > p");
			Elements searchDtOcorrencoa = document.select(".heading-date");
			String dsData = searchDtOcorrencoa.text();

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

				System.out.println(result.text()+"\n");
			}
			
			searchResults.clear();
			searchDtOcorrencoa.clear();
			document = null;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

package br.com.furb.test;

import java.util.List;

import javax.persistence.Query;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;
import br.com.furb.textMining.AnalisarInformacao;
import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;

public class TesteClassificacao {
	
	public static void main(String[] args) {
		
		try {
			ConnectionDB conection = new ConnectionDB();
			Stemmer stemmer = new OrengoStemmer();
			
			String sql = "FROM AtividadePolicial a where id_natureza_ocorr = 19 ";
			
			Query query = conection.getManager().createQuery(sql);
			
			List<AtividadePolicial> atividadePoliciais = query.getResultList();
			
			for (AtividadePolicial atividadePolicial : atividadePoliciais) {
				AnalisarInformacao analisar = new AnalisarInformacao("", "", "", null, stemmer);
				
				System.out.println(atividadePolicial.getDsFato());
				
				analisar.classificarOcorrencia(atividadePolicial.getDsFato());
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}

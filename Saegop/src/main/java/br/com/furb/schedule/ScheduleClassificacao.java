package br.com.furb.schedule;

import java.util.List;

import javax.persistence.Query;

import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;
import br.com.furb.controller.AtividadePolicialController;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;
import br.com.furb.textMining.AnalisarInformacao;

public class ScheduleClassificacao {
	
	public static void main(String[] args) {
		
		try {
			ConnectionDB conection = new ConnectionDB();
			AtividadePolicialController atividadePolicController = new AtividadePolicialController(conection);
			
			String sql = "FROM AtividadePolicial a where length(dsFato) > 40 ";
					
			Query query = conection.getManager().createQuery(sql);
			
			List<AtividadePolicial> atividadePoliciais = query.getResultList();
			
			Stemmer stemmer = new OrengoStemmer();
			
			for (AtividadePolicial atividadePolicial : atividadePoliciais) {
				AnalisarInformacao analisar = new AnalisarInformacao("", "", "", null, stemmer);
				
				atividadePolicial.setNaturezaOcorrencia(analisar.classificarOcorrencia(atividadePolicial.getDsFato()));								
				
			}
			
			atividadePolicController.atualizarClassificacoes(atividadePoliciais);
			
			System.out.println("FIM");
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
		
		
		
	}

}



package br.com.furb.schedule;


import java.util.List;

import javax.persistence.Query;

import ptstemmer.Stemmer;
import ptstemmer.implementations.OrengoStemmer;
import br.com.furb.controller.AtividadePolicialSispController;
import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicialSisp;
import br.com.furb.sisp.ClassificarSisp;

public class ScheduleClassificacaoSisp {
	
	public static void main(String[] args) {
		
		try {
			
			ConnectionDB conection = new ConnectionDB();
			
			AtividadePolicialSispController atividadePolicSispController = new AtividadePolicialSispController(conection); 
			
			String sql = "FROM AtividadePolicialSisp a where dsTipificacao is not null ";
					
			Query query = conection.getManager().createQuery(sql);
			
			List<AtividadePolicialSisp> listAtividadePolicialSisp = query.getResultList();
			
			Stemmer stemmer = new OrengoStemmer();
			
			for (AtividadePolicialSisp atividadePolicialSisp : listAtividadePolicialSisp) {
				
				ClassificarSisp classificarSisp = new ClassificarSisp(stemmer);
				atividadePolicialSisp.setNaturezaOcorrenciaSisp(classificarSisp.classificar(atividadePolicialSisp.getDsTipificacao()));				
			}
			
			System.out.println("INICIOU");
			
			atividadePolicSispController.atualizarClassificacoes(listAtividadePolicialSisp);
			
			System.out.println("TERMINOU");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally{
			System.exit(0);
		}
		
	}

}

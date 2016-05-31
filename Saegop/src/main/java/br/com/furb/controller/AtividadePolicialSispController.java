package br.com.furb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicialSisp;

public class AtividadePolicialSispController {
	
	private ConnectionDB conection;
	
	public AtividadePolicialSispController(
			ConnectionDB conection) {
		this.conection = conection;
	}
	
	public void inserir(ArrayList<AtividadePolicialSisp> listaAtividades){
		conection.getManager().getTransaction().begin();
		
		int i = 0;
		
		for (AtividadePolicialSisp atividadePolicial : listaAtividades) {
			
			conection.getManager().persist(atividadePolicial);
			
			if	((i % 1000) == 0){
				conection.getManager().flush();
				conection.getManager().clear();
			}
			
			i++;
		}
		
		conection.getManager().getTransaction().commit();
	}
	
	public void deletarAtividadesSisp(){
		conection.getManager().getTransaction().begin();
		Query query = conection.getManager().createQuery(
				"from AtividadePolicialSisp as a");
		for (Object obj : query.getResultList()) {
			conection.getManager().remove(obj);
		}
		conection.getManager().getTransaction().commit();
	}
	
	public void atualizarClassificacoes(List<AtividadePolicialSisp> listaAtividades){
		conection.getManager().getTransaction().begin();
		
		for (AtividadePolicialSisp atividadePolicialSisp : listaAtividades) {
			conection.getManager().merge(atividadePolicialSisp);
		}
		
		conection.getManager().getTransaction().commit();
	}


}

package br.com.furb.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;

public class AtividadePolicialController {
	
	private ConnectionDB conection;
	
	public AtividadePolicialController(
			ConnectionDB conection) {
		this.conection = conection;
	}
	
	public void inserir(ArrayList<AtividadePolicial> listaAtividades){
		conection.getManager().getTransaction().begin();
		
		for (AtividadePolicial atividadePolicial : listaAtividades) {
			conection.getManager().persist(atividadePolicial);
		}
		
		conection.getManager().getTransaction().commit();
	}
	
	public void deletarAtividades() {
		conection.getManager().getTransaction().begin();
		Query query = conection.getManager().createQuery(
				"from AtividadePolicial as a");
		for (Object obj : query.getResultList()) {
			conection.getManager().remove(obj);
		}
		conection.getManager().getTransaction().commit();
	}
	
	public void atualizarClassificacoes(List<AtividadePolicial> listaAtividades){
		conection.getManager().getTransaction().begin();
		
		for (AtividadePolicial atividadePolicial : listaAtividades) {
			conection.getManager().merge(atividadePolicial);
		}
		
		conection.getManager().getTransaction().commit();
	}

}

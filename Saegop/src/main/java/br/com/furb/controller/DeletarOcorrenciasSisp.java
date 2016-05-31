package br.com.furb.controller;

import javax.persistence.Query;

import br.com.furb.dao.ConnectionDB;

public class DeletarOcorrenciasSisp {
	
	private ConnectionDB conection;
	
	public DeletarOcorrenciasSisp( ConnectionDB conection){
		this.conection = conection;
	}
	
	public void removeAllInstances() {
		conection.getManager().getTransaction().begin();
		Query query = conection.getManager().createQuery(
				"from AtividadePolicialSisp as a");
		for (Object obj : query.getResultList()) {
			conection.getManager().remove(obj);
		}
		conection.getManager().getTransaction().commit();
	}

}

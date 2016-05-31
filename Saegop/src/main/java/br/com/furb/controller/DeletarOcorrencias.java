package br.com.furb.controller;

import javax.persistence.Query;

import br.com.furb.dao.ConnectionDB;

public class DeletarOcorrencias {
	
	private ConnectionDB conection;
	
	public DeletarOcorrencias( ConnectionDB conection){
		this.conection = conection;
	}
	
	public void removeAllInstances() {
		conection.getManager().getTransaction().begin();
		Query query = conection.getManager().createQuery(
				"from AtividadePolicial as a");
		for (Object obj : query.getResultList()) {
			conection.getManager().remove(obj);
		}
		conection.getManager().getTransaction().commit();
	}

}

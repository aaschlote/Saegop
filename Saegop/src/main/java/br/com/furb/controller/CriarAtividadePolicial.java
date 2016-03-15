package br.com.furb.controller;

import java.util.ArrayList;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicial;

public class CriarAtividadePolicial {
	
	private ArrayList<AtividadePolicial> listaAtividades;
	private ConnectionDB conection;
	
	public CriarAtividadePolicial(ArrayList<AtividadePolicial> listaAtividades,
			ConnectionDB conection) {
		this.listaAtividades = listaAtividades;
		this.conection = conection;
	}
	
	public void inserir(){
		conection.getManager().getTransaction().begin();
		
		for (AtividadePolicial atividadePolicial : listaAtividades) {
			conection.getManager().merge(atividadePolicial);
		}
		
		conection.getManager().getTransaction().commit();
	}

}

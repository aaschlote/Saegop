package br.com.furb.controller;

import java.util.ArrayList;

import br.com.furb.dao.ConnectionDB;
import br.com.furb.model.AtividadePolicialSisp;

public class CriarAtividadePolicialSisp {
	
	private ArrayList<AtividadePolicialSisp> listaAtividades;
	private ConnectionDB conection;
	
	public CriarAtividadePolicialSisp(ArrayList<AtividadePolicialSisp> listaAtividades,
			ConnectionDB conection) {
		this.listaAtividades = listaAtividades;
		this.conection = conection;
	}
	
	public void inserir(){
		conection.getManager().getTransaction().begin();
		
		for (AtividadePolicialSisp atividadePolicial : listaAtividades) {
			conection.getManager().persist(atividadePolicial);
		}
		
		conection.getManager().getTransaction().commit();
	}


}

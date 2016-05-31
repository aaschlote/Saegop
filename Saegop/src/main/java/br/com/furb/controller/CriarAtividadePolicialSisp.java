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


}

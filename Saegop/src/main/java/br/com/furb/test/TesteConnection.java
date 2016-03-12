package br.com.furb.test;

import br.com.furb.dao.ConnectionDB;


public class TesteConnection {
	
	public static void main(String[] args) {
		try {
			ConnectionDB user = new ConnectionDB();
			System.out.println("CONECTADO");
		} catch (Exception e) {
			System.out.println("NÃO CONECTADO \n" + e.getMessage());
			e.printStackTrace();
		}
	}

}

package br.com.furb.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionDB {
	
	private EntityManagerFactory factory; 
	private EntityManager manager;
	
	public ConnectionDB(){
		factory = new Persistence().createEntityManagerFactory("connection");
		manager = factory.createEntityManager();
	}

}

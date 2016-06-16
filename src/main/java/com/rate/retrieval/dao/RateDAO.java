package com.rate.retrieval.dao;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RateDAO {

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	public void inv(){
		
	}

}

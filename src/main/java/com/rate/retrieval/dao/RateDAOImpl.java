package com.rate.retrieval.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.rate.retrieval.model.Rate;

@Repository("rateDao")
public class RateDAOImpl implements RateDAO{

	private SessionFactory sessionFactory;
	private Transaction tx;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	public Rate findById(int id) {
		Session session = sessionFactory.openSession();
    	return (Rate) session.get(Rate.class, id);
    }
 
	public void saveRates(List<Rate> rates) {
		Session session = sessionFactory.openSession();
		try {

			session.beginTransaction();
			int i = 0;
			for (Rate rate : rates) {
				session.save(rate);
				if (i++ % 50 == 0) {
					session.flush();
					session.clear();
				}
			}
			// session.persist(entity);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		// persist(rate);
	}
 
    public void deleteRateByDate(String date) {
    	Session session = sessionFactory.openSession();
    	
        Query query = session.createSQLQuery("delete from Rate where date = :date");
        query.setString("date", date);
        query.executeUpdate();
    }
 
    @SuppressWarnings("unchecked")
    public List<Rate> findAllRates() {
        Criteria criteria = sessionFactory.openSession().createCriteria(Rate.class);
        return (List<Rate>) criteria.list();
    }
 
    public List<Rate> findRateByDate(String date) {
        Criteria criteria = sessionFactory.openSession().createCriteria(Rate.class);
        criteria.add(Restrictions.eq("timestamp", date));
        return criteria.list();
    }

}

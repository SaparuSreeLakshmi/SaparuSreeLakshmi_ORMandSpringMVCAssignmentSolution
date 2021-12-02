package com.customermanagement.Service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.customermanagement.entities.Customers;

@Service
public class ServiceImplementation implements CustServices {
	private SessionFactory sessionFactory;

	private Session session;

	@Autowired
	public ServiceImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	@Override
	public List<Customers> findAll() {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		List<Customers> customers = session.createQuery("from Customers").list();
		tx.commit();
		return customers;
	}

	@Override
	@Transactional
	public Customers findById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Customers customer = session.get(Customers.class, id);
		tx.commit();
		return customer;
	}

	@Override
	public void save(Customers cust) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(cust);
		tx.commit();

	}

	@Override
	@Transactional
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		Transaction tx = session.beginTransaction();
		Customers cust = session.get(Customers.class, id);
		session.delete(cust);
		tx.commit();

	}

}

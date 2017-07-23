package com.dhl.chatbot.persistence;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.dhl.chatbot.util.FileFinder;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class HibernateSession implements ISession {
	
	private static HibernateSession hibernateSession = new HibernateSession();
	
	static {
		Configuration config = getConfiguration();
		sessionFactory = config.buildSessionFactory();
	}
	
	private HibernateSession() {
		
	}
		
	public static HibernateSession getInstance() {
		return hibernateSession;
	}
	
	private static SessionFactory sessionFactory = null;
	
	private static Configuration configuration = null;
	
	private static Session buildSession() {
		if(sessionFactory == null) {
			sessionFactory = getConfiguration().buildSessionFactory();
		}
		return sessionFactory.openSession();
	}
	
	private static Configuration getConfiguration() {
		if(configuration == null) {
			configuration = new Configuration();
//			String path = System.getProperty("user.dir");
			File file = new FileFinder().getHibernateFile();
			configuration = configuration.configure(file);
//			File file = new FileFinder().getHibernateFile();
//			List<URL> urls = new FileFinder().getHibernateResourceURL();
//			for (URL url : urls) {
//				if (! url.getPath().endsWith("hibernate.cfg.xml")) {
//					configuration = configuration.addURL(url);
//				} else {
//					configuration = configuration.configure(url);
//				}
//			}
		}
		
		return configuration;
	}


	public static Session getSession() {
		if(sessionFactory == null) {
			buildSession();
		}
		return sessionFactory.openSession();
	}
	
	public void insert(Object object) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSession.getSession();
			transaction = session.beginTransaction();
			session.save(object);
			transaction.commit();
		} catch (Throwable throwable) {
			transaction.rollback();
			throw new Exception(throwable.getMessage());
		} finally {
			session.close();
		}
	}
	
	public void delete(Object object) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSession.getSession();
			transaction = session.beginTransaction();
			session.delete(object);
			transaction.commit();
		} catch (Throwable throwable) {
			transaction.rollback();
			throw new Exception(throwable.getMessage());
		} finally {
			session.close();
		}	
			
	}

	public void update(Object object) throws Exception {
		Session session = null;
		Transaction transaction = null;
		try {
			session = HibernateSession.getSession();
			transaction = session.beginTransaction();
			session.update(object);
			transaction.commit();
		} catch (Throwable throwable) {
			transaction.rollback();
			throw new Exception(throwable.getMessage());
		} finally {
			session.close();
		}
		
	}
	
	private Object load(Class<? extends Serializable> clazz, Serializable object) throws Exception {
		Session session = null;
		try {
			session = HibernateSession.getSession();
			object = (Serializable) session.load(clazz, object);
		} catch (Throwable throwable) {
			throw new Exception(throwable.getMessage());
		} finally {
//			session.close();
		}
		return object;
	}
		
	private Object get(Class<? extends Serializable> clazz, Serializable object) throws Exception {
		Session session = null;
		try {
			session = HibernateSession.getSession();
			object = (Serializable) session.get(clazz, object);
		} catch (Throwable throwable) {
			throw new Exception(throwable.getMessage());
		} finally {
//			session.close();
		}
		return object;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> executeQuery(String queryName, Map<String, Object> parameterList) throws Exception {
		Session session = null;
		try {
			session = HibernateSession.getSession();
			session.flush();
		} catch (Throwable throwable) {
			throw new Exception(throwable.getMessage());
		} finally {
//			session.close();
		}
		Query query = session.getNamedQuery(queryName);
		if (parameterList != null) {
			for (String key : parameterList.keySet()) {
				query.setParameter(key, parameterList.get(key));	
			}
		}
		return query.list();
	}
	
	public int executeUpdate(String queryName, Map<String, Object> parameterList) throws Exception {
		Session session = null;
		try {
			session = HibernateSession.getSession();
			session.flush();
		} catch (Throwable throwable) {
			throw new Exception(throwable.getMessage());
		} finally {
//			session.close();
		}
		Query query = session.getNamedQuery(queryName);
		for (String key : parameterList.keySet()) {
			query.setParameter(key, parameterList.get(key));	
		}
		return query.executeUpdate();
	}

	/* (non-Javadoc)
	 * @see sri.ram.mandir.persistence.ISession#get(java.lang.Object)
	 */
	@Override
	public Object get(Object object1) throws Exception {
		Serializable object = (Serializable)object1;
		object = (Serializable) HibernateSession.getInstance().get(object.getClass(), object);
		return object;
	}

	/* (non-Javadoc)
	 * @see sri.ram.mandir.persistence.ISession#load(java.lang.Object)
	 */
	@Override
	public Object load(Object object1) throws Exception {
		Serializable object = (Serializable)object1;
		object = (Serializable) HibernateSession.getInstance().load(object.getClass(), object);
		return object;
	}
	
}

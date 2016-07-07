package org.scs;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public void hibSave() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Member m = new Member();
		m.setWriter("tester");
		m.setContent("Hello World");
		
		session.save(m);
		
		session.getTransaction().commit();
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
		HibernateTest ht = new HibernateTest();
		ht.hibSave();
	}
}

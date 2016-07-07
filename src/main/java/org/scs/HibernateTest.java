package org.scs;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateTest {
	SessionFactory factory = HibernateUtil.getSessionFactory();
	
	public void hibSave() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Member m = new Member();
		m.setWriter("tester1");
		m.setContent("Hello World1");
		
		session.save(m);
		
		session.getTransaction().commit();
	}
	
	public Member selectOne(int no) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Member member = session.get(Member.class, no);
		
		System.out.println("번호 : " + member.getNo());
		System.out.println("글쓴이 : " + member.getWriter());
		System.out.println("내용 : " + member.getContent());
		
		session.getTransaction().commit();
		
		return member;
	}
	
	public void selectAll() {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		Query query = session.createQuery("from Member");
		List<Member> list = query.getResultList();
		
		for(int i = 0; i < list.size() ; i++) {
			System.out.println(list.get(i).getNo() + ", " + list.get(i).getWriter() + ", " + list.get(i).getContent());
		}
		
		session.getTransaction().commit();
	}
	
	public void update(Member mem) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		mem.setWriter("testerUpdate");
		mem.setContent("contentUpdate");
		
		session.update(mem);;
		
		session.getTransaction().commit();
	}
	
	public void delete(Member mem) {
		int no = mem.getNo();
		
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		
		session.delete(mem);
		
		session.getTransaction().commit();
		
		System.out.println(no + "번 삭제 됨.");
	}
	
	public static void main(String[] args) {
		System.out.println("Hello");
		HibernateTest ht = new HibernateTest();
//		ht.hibSave();
		ht.selectOne(1);
		ht.selectAll();
		ht.update(ht.selectOne(1));
		System.out.println("업데이트 후");
		ht.selectOne(1);
		ht.delete(ht.selectOne(2));
	}
}

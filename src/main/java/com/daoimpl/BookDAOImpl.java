package com.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.dao.BookDAO;
import com.pojos.Book;

public class BookDAOImpl implements BookDAO {

	StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  

	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
	 
	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
	

	
	
	public void addBook(Book book)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();  
		
		session.save(book);
		
		t.commit();
		session.close();
	}
	
	
	
	public Book getById(Integer id)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();  
		
		Book book=session.get(Book.class, id);
		
		t.commit();
		
		session.close();
		return book;
	}
	
	
	
	public void update(Book book)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();  
		
		session.saveOrUpdate(book);
		
		t.commit();
		
		session.close();
		
	}
	
	
	
	public void delete(Book book)
	{
		System.out.println("BOOKDAOIMPL DELETE METHOD IS CALLING");
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();  
		
		Integer id=book.getBookId();
		
		String hql = "update Book b set b.status=0 where b.bookId ='"+id+"'";
        Query query = session.createQuery(hql);
        int count = query.executeUpdate();			
		
		t.commit();
		
		session.close();
	}
	
	
	
	public List<Book> getAll()
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();  
		
		String hql = "from Book b where b.status=1";
		
		Query query = session.createQuery(hql);
		
		List<Book> list = query.list();
		
		t.commit();
		session.close();
		
		return list;
	}

}

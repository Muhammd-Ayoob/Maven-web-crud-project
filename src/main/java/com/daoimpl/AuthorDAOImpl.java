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

import com.dao.AuthorDao;
import com.pojos.Author;

public class AuthorDAOImpl implements AuthorDao {

StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();  
    
	Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
	 
	SessionFactory factory = meta.getSessionFactoryBuilder().build();  
	
	
	//Method to add Data in database
	
	public void addAuthor(Author author)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		session.save(author);
		
		t.commit();
		
		session.close();
	}
	
	
	
	//Method to get Object by id
	
	public Author getById(int id)
    {
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		Author author=session.get(Author.class, id);
		
		t.commit();
		
		session.close();
		
		return author; 
    }
	
	
	//Method to update a record
	public void update(Author author)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		session.saveOrUpdate(author);
		
		t.commit();
		session.close();
	}
	
	//Method to delete the record
	public void delete(Author author)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		Integer id=author.getAuthorId();
		
		String hql = "update Author a set a.status=0 where a.authorId ='"+id+"'";
        Query query = session.createQuery(hql);
        int count = query.executeUpdate();
		
		t.commit();
		
		session.close();
	}
	
	
	
	public List<Author> getAll()
	{

		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();

		String hql = "from Author a where a.status=1";
		Query query = session.createQuery(hql);
		List<Author> list = query.list();
		
		t.commit();
		
		session.close();
		
		this.getClass().getClassLoader();
		
		return list;
	}
	
	
	
	
	
	
	
	public Integer getIdByName(String name)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		Query query=session.createQuery("from Author where authorName='"+name+"'");
		
		List<Author> list=query.list();
		
		Author author=list.iterator().next();
		
		Integer id=author.getAuthorId();
		
		t.commit();
		
		session.close();
		
		return id;
	}
	
	
	
	
	
	
	public Author getByName(String name)
	{
		Session session = factory.openSession();  
		
		Transaction t = session.beginTransaction();
		
		Query query=session.createQuery("from Author where authorName='"+name+"'");
		
		List<Author> list=query.list();
		
		Author author=list.iterator().next();
	
		t.commit();
		
		session.close();
		
		return author;
	}
	
}

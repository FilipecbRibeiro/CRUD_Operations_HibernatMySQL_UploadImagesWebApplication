package org.light.hibernate.data_acess_objec;



import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.light.hibernate.entity.Files;


public class FilesDAO {


	
//	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Files.class).buildSessionFactory();
	
	SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Files.class)
			.buildSessionFactory();

	//Create object of Entity Class
	
	//Start Transaction
	
	//Perform Operation
	
	//Commit Transaction
	
	public void addFiles(Files file) {
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		session.save(file);
	 session.getTransaction().commit();
		System.out.println(file.getFileName());
		
	
	}
	@SuppressWarnings("unchecked")
	public List<Files> getList(){
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		List<Files> tempFiles= new LinkedList<Files>();
		tempFiles=session.createQuery("from files").getResultList();//this is the name of the@ENTITY FILES!
//		session.getTransaction().commit();
		
		return tempFiles;
		
	}
	public void updateInformation(int id, String label, String caption) {
		Session session=factory.getCurrentSession();
		Files file= new Files();
		session.beginTransaction();
		file=session.get(Files.class, id);
		file.setLabel(label);
		file.setCaption(caption);
		session.getTransaction().commit();
		
		
	}
	public Files getFile(int id) {
		Files file =new Files();
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		file = session.get(Files.class, id);
    	session.getTransaction().commit();
		
		return file;
	}
	public void deleteFile(int fileId) {
		Session session = factory.getCurrentSession();
	
		session.beginTransaction();
		Files file = session.get(Files.class, fileId);	
		session.delete(file);
		session.getTransaction().commit();
		
	}
}							

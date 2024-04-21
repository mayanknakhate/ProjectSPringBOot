package com.work.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.work.Model.Category;

import jakarta.persistence.EntityManager;




@Repository
public class ReDaoimpl implements ReDao {
	@Autowired
	SessionFactory sessionfactory;
	@Autowired
	EntityManager entitymanager;
	@Transactional
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
	Session session = entitymanager.unwrap(Session.class);
	session.save(category);

	}
	@Transactional
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		Session session = entitymanager.unwrap(Session.class);
		return session.createQuery("from Category").list();
		
	}
	@Transactional
	@Override
	public int deleteCategry(int id) {
		// TODO Auto-generated method stub 
		try {
			System.out.println(id);
		//Session session = sessionfactory.openSession();
		Session session = entitymanager.unwrap(Session.class);
		 Category categoryToDelete = session.get(Category.class, id);
		session.delete(categoryToDelete);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	@Override
	public Category updateCategry(int id) {
		// TODO Auto-generated method stub
		Session session = entitymanager.unwrap(Session.class);
		Category em = (Category) session.get(Category.class, id);
		return em;
		
	}
	@Transactional
	@Override
	public void updateCate(Category c) {
		// TODO Auto-generated method stub
		Session session = entitymanager.unwrap(Session.class);
		
		session.update(c);
	}

}

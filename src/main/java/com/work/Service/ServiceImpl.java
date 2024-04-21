package com.work.Service;

import com.work.Model.Category;
import com.work.dao.ReDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ServiceImpl implements UserService{
@Autowired
ReDao dao;
	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
	dao.saveCategory(category);

	}
	@Override
	public List<Category> getAllCategory() {
		// TODO Auto-generated method stub
		
		return dao.getAllCategory();
	}
	@Override
	public int deleteCategry(int id) {
		// TODO Auto-generated method stub
		dao.deleteCategry(id);
		return 0;
	}
	@Override
	public Category editCategry(int id) {
		// TODO Auto-generated method stub
		Category c=dao.updateCategry(id);
		return c;
	}
	@Override
	public void updateCate(Category c) {
		// TODO Auto-generated method stub
		dao.updateCate(c);
	}

}

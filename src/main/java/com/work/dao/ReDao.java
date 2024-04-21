package com.work.dao;

import java.util.List;

import com.work.Model.Category;

public interface ReDao {
	public void saveCategory(Category category);
	public List<Category> getAllCategory();
	public int deleteCategry(int id);
	public Category updateCategry(int id);	
	public void updateCate(Category c);
		
		
	

}

package com.work.Service;

import java.util.List;

import com.work.Model.Category;

public interface UserService {
	public void saveCategory(Category category);
	public List<Category> getAllCategory();
	public int deleteCategry(int id);
	public Category editCategry(int id);
	public void updateCate(Category c);
	
}

package hu.neuron.database.service;

import java.util.List;

import hu.neuron.database.dao.CategoryDao;
import hu.neuron.database.entity.Category;

public class CategoryService {

	private static CategoryDao categoryDao;
	 
    public CategoryService() {
        categoryDao = new CategoryDao();
    }
 
    public void persist(Category entity) {
        categoryDao.openCurrentSessionwithTransaction();
        categoryDao.persist(entity);
        categoryDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Category entity) {
        categoryDao.openCurrentSessionwithTransaction();
        categoryDao.update(entity);
        categoryDao.closeCurrentSessionwithTransaction();
    }
 
    public Category findById(int id) {
        categoryDao.openCurrentSession();
        Category category = categoryDao.findById(id);
        categoryDao.closeCurrentSession();
        return category;
    }
 
    public void delete(long id) {
        categoryDao.openCurrentSessionwithTransaction();
        Category category = categoryDao.findById(id);
        categoryDao.delete(category);
        categoryDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Category> findAll() {
        categoryDao.openCurrentSession();
        List<Category> category = categoryDao.findAll();
        categoryDao.closeCurrentSession();
        return category;
    }
 
    public void deleteAll() {
        categoryDao.openCurrentSessionwithTransaction();
        categoryDao.deleteAll();
        categoryDao.closeCurrentSessionwithTransaction();
    }
 
    public CategoryDao getCategoryDao() {
        return categoryDao;
    }
}

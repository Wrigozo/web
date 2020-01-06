package hu.neuron.database.service;

import java.util.List;

import hu.neuron.database.dao.ProductDao;
import hu.neuron.database.entity.Product;

public class ProductService {

	private static ProductDao productDao;
	 
    public ProductService() {
        productDao = new ProductDao();
    }
 
    public void persist(Product entity) {
        productDao.openCurrentSessionwithTransaction();
        productDao.persist(entity);
        productDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Product entity) {
        productDao.openCurrentSessionwithTransaction();
        productDao.update(entity);
        productDao.closeCurrentSessionwithTransaction();
    }
 
    public Product findById(int id) {
        productDao.openCurrentSession();
        Product product = productDao.findById(id);
        productDao.closeCurrentSession();
        return product;
    }
 
    public void delete(int id) {
        productDao.openCurrentSessionwithTransaction();
        Product product = productDao.findById(id);
        productDao.delete(product);
        productDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Product> findAll() {
        productDao.openCurrentSession();
        List<Product> products = productDao.findAll();
        productDao.closeCurrentSession();
        return products;
    }
 
    public void deleteAll() {
        productDao.openCurrentSessionwithTransaction();
        productDao.deleteAll();
        productDao.closeCurrentSessionwithTransaction();
    }
 
    public ProductDao getProductDao() {
        return productDao;
    }
}

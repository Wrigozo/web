package hu.neuron.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.dao.CategoryDao;
import hu.neuron.database.repository.dao.ProductDao;
import hu.neuron.database.repository.dao.UnitDao;
import hu.neuron.database.repository.daoimpl.CategoryDaoImpl;
import hu.neuron.database.repository.daoimpl.ProductDaoImpl;
import hu.neuron.database.repository.daoimpl.UnitDaoImpl;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("serial")
@ManagedBean(name="product")
@SessionScoped
@Getter
@Setter
@NoArgsConstructor
public class ProductBean implements Serializable{
	
	private ProductDao productDao=new ProductDaoImpl();
	private CategoryDao categoryDao=new CategoryDaoImpl();
	private UnitDao unitDao=new UnitDaoImpl();

	private Category category;
	private Unit unit;
	private String search;
	private Product selectedProduct;
	
	private  List<Product> productValues;
	private  List<Category> categoryValue;
	private  List<Unit> unitValue;	
	
	/**
	 * The current page.
	 */
	private int currentpage;
	
	/**
	 * This is the total number of pages
	 */
	private int numberOfPages;
	
	/**
	 * The number of the products per page.
	 */
	private int recordsPerPage;
	
	/**
	 * The number of all the filtered products.
	 */
	private int rows;
	
	
	@PostConstruct
	public void init() {
		productValues = productDao.findAll();
		categoryValue = categoryDao.findAll();
		unitValue = unitDao.findAll();
		rows = productDao.getNumberOfRows(category, unit, search);
	}
	
	public void filter(String category, String unit, String search, int currentpage, int recordsPerPage) {
		this.category=categoryDao.findCategoryByName(category);
		this.unit=unitDao.findUnitByName(unit);
		this.search=search;
		this.currentpage=currentpage;
		this.rows=recordsPerPage;
		
		numberOfPages = rows / recordsPerPage;

		// a két if sorrendje fontos
		if (rows % recordsPerPage > 0) {
			numberOfPages++;
		}

		if (currentpage > numberOfPages) {
			currentpage = 1;
		}
		
		this.productValues=productDao.getByMultipleParameter(this.category, this.unit, recordsPerPage, currentpage, search);
	}

}

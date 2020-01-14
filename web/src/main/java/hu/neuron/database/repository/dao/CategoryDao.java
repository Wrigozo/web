package hu.neuron.database.repository.dao;

import hu.neuron.database.entity.Category;
import hu.neuron.database.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDao extends GenericDao<Category>{

	public CategoryDao() {
		super(Category.class);
	}
	
	
	public Category findCategoryByName(String name) {
		if(name.equals("")) {
			return null;
		}
		Category entity = entityManager
				.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name = :name", entityClass)
				.setParameter("name", name).getSingleResult();
		return entity != null ? entity : null;
	}
	
}

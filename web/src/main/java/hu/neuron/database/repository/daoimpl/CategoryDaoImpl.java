package hu.neuron.database.repository.daoimpl;

import hu.neuron.database.entity.Category;
import hu.neuron.database.repository.dao.CategoryDao;
import hu.neuron.database.repository.dao.GenericDao;
import hu.neuron.warehouse.client.api.CategoryVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDaoImpl extends GenericDaoImpl<Category, CategoryVO> implements CategoryDao{

	public CategoryDaoImpl() {
		super(Category.class, CategoryVO.class);
	}

	@Override
	public Category findCategoryByName(String name) {
		if (name.equals("")) {
			return null;
		}
		Category entity = entityManager
				.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name = :name", entityClass)
				.setParameter("name", name).getSingleResult();
		return entity != null ? entity : null;
	}

}

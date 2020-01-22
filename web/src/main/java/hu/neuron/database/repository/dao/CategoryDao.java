package hu.neuron.database.repository.dao;

import hu.neuron.database.entity.Category;
import hu.neuron.warehouse.client.api.CategoryVO;

public interface CategoryDao extends GenericDao<Category, CategoryVO>{

	Category findCategoryByName(String name);

}
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
}

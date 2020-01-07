package hu.neuron.database.repository;


import hu.neuron.database.entity.Category;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDao extends GenericDao<Category>{

	public CategoryDao() {
		super(Category.class);
	}
}

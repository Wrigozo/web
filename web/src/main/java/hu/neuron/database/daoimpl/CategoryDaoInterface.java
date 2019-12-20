package hu.neuron.database.daoimpl;

import java.util.List;

public interface CategoryDaoInterface<T> {

public void persist(T entity);
    
    public void update(T entity);
     
    public T findById(long id);
     
    public void delete(T entity);
     
    public List<T> findAll();
     
    public void deleteAll();
}

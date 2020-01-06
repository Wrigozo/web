package hu.neuron.database.service;

import java.util.List;

import hu.neuron.database.dao.UnitDao;
import hu.neuron.database.entity.Unit;

public class UnitService {

	private static UnitDao unitDao;
	 
    public UnitService() {
    	unitDao = new UnitDao();
    }
 
    public void persist(Unit entity) {
    	unitDao.openCurrentSessionwithTransaction();
    	unitDao.persist(entity);
    	unitDao.closeCurrentSessionwithTransaction();
    }
 
    public void update(Unit entity) {
    	unitDao.openCurrentSessionwithTransaction();
    	unitDao.update(entity);
    	unitDao.closeCurrentSessionwithTransaction();
    }
 
    public Unit findById(int id) {
    	unitDao.openCurrentSession();
        Unit unit = unitDao.findById(id);
        unitDao.closeCurrentSession();
        return unit;
    }
 
    public void delete(long id) {
    	unitDao.openCurrentSessionwithTransaction();
        Unit unit = unitDao.findById(id);
        unitDao.delete(unit);
        unitDao.closeCurrentSessionwithTransaction();
    }
 
    public List<Unit> findAll() {
    	unitDao.openCurrentSession();
        List<Unit> unit = unitDao.findAll();
        unitDao.closeCurrentSession();
        return unit;
    }
 
    public void deleteAll() {
    	unitDao.openCurrentSessionwithTransaction();
    	unitDao.deleteAll();
    	unitDao.closeCurrentSessionwithTransaction();
    }
 
    public UnitDao getUnitDao() {
        return unitDao;
    }
}

package de.timoklostermann.refuel.datastore.dao;

import java.util.List;

import javax.jdo.Query;

import de.timoklostermann.refuel.datastore.entity.User;
import de.timoklostermann.refuel.datastore.entity.Vehicle;
import de.timoklostermann.refuel.datastore.interfaces.Entity;

public class VehicleDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) VehicleDAO.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vehicle> findByUser(User user) {
        final Query query = pm.newQuery(Vehicle.class);
        query.setFilter("this.user == user");
        query.declareParameters(User.class.getName() + " user");
        List<Vehicle> vehicles = null;
        try {
        	vehicles = (List<Vehicle>) query.execute(user);
        } finally {
        	query.closeAll();
        }
        return vehicles;
	}
	
	public Vehicle saveOrUpdate(Vehicle vehicle) {
		Vehicle v = this.findByUserAndName(vehicle.getUser(), vehicle.getName());
		if(v == null) {
			return this.persist(vehicle);
		} else {
			this.delete(v);
			return this.persist(vehicle);
		}
	}
	
	@SuppressWarnings("unchecked")
	private Vehicle findByUserAndName(User user, String name) {
        final Query query = pm.newQuery(Vehicle.class);
        query.setFilter("this.user == user && this.name == name");
        query.declareParameters(User.class.getName() +  " user, String name");
        List<Vehicle> vehicles = null;
        try {
        	vehicles = (List<Vehicle>) query.execute(user, name);
        } finally {
        	query.closeAll();
        }
        if(vehicles.isEmpty()) {
        	return null;
        } else {
        	return vehicles.get(0);
        }
	}
}

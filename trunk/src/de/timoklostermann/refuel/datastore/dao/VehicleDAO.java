package de.timoklostermann.refuel.datastore.dao;

import de.timoklostermann.refuel.datastore.interfaces.Entity;

public class VehicleDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) VehicleDAO.class;
	}	
}

package de.timoklostermann.refuel.datastore.dao;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import de.timoklostermann.refuel.datastore.entity.Filling;
import de.timoklostermann.refuel.datastore.entity.Vehicle;
import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.util.PMF;

public class FillingDAO extends AbstractDAO {

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) FillingDAO.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<Filling> findByVehicle(Vehicle vehicle) {
		final PersistenceManager pManager = PMF.get().getPersistenceManager();
        final Query query = pManager.newQuery(Filling.class);
        query.setFilter("this.vehicle == vehicle");
        query.declareParameters("de.timoklostermann.refuel.datastore.entity.Vehicle vehicle");
        List<Filling> fillings = null;
        try {
        	fillings = (List<Filling>) query.execute(vehicle);
        } finally {
        	query.closeAll();
        }
        return fillings;
	}
}

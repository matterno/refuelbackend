package de.timoklostermann.refuel.datastore.dao;

import java.util.logging.Logger;

import de.timoklostermann.refuel.datastore.interfaces.Entity;

public class FillingDAO extends AbstractDAO {
	/**
	 * The Logger.
	 */
	private static final Logger LOG = Logger.getLogger(FillingDAO.class.getName());

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) FillingDAO.class;
	}


}

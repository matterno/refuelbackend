package de.timoklostermann.datastore.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import de.timoklostermann.datastore.entity.Filling;
import de.timoklostermann.datastore.interfaces.Entity;
import de.timoklostermann.util.PMF;

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

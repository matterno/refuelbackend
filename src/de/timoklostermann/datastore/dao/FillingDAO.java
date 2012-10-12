package de.timoklostermann.datastore.dao;

import java.util.logging.Logger;

import javax.jdo.PersistenceManager;

import de.timoklostermann.datastore.entity.Filling;
import de.timoklostermann.util.PMF;

public class FillingDAO {
	/**
	 * The Logger.
	 */
	private static final Logger LOG = Logger.getLogger(FillingDAO.class.getName());
	
	/**
	 * The {@link PersistenceManager}
	 */
	protected PersistenceManager pm;
	
	/**
	 * Writes (adds or updates) the given entitiy to the datastore.
	 * @param entity The entity to write.
	 * @return {@link Filling}
	 */
	public Filling persist(final Filling entity) {
		if(this.pm != null || !this.pm.isClosed()) {
			this.pm.close();
		}
		pm = PMF.getInstance().getPersistenceManager();
		Filling result = null;
		
		result = this.pm.makePersistent(entity);
		this.pm.flush();
		
		return result;
	}
}

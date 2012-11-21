package de.timoklostermann.refuel.datastore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.JDOObjectNotFoundException;
import javax.jdo.ObjectState;
import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.Key;

import de.timoklostermann.refuel.common.Constants;
import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.util.PMF;

public abstract class AbstractDAO {
	
	/**
	 * The persistance manager for queries.
	 * Protected so that all DAOs can use this.
	 */
	protected PersistenceManager pm = PMF.get().getPersistenceManager();
	
	/**
	 * Logger.
	 */
	private static final Logger LOG = Logger.getLogger(AbstractDAO.class.getName());
	
	/**
	 * Needed so every child can tell the call it representatives.
	 * @return
	 */
	public abstract <T extends Entity> Class<T> getEntityClass();
	
	/**
	 * Delets the given entity.
	 * @param entity
	 */
	public <T extends Entity> void delete(T entity) {
		Transaction tx = this.pm.currentTransaction();
		tx.begin();
		final ObjectState state = JDOHelper.getObjectState(entity);
		LOG.info(Constants.ENTITY_STATE + state.toString());
		if (state.equals(ObjectState.TRANSIENT)
				|| state.equals(ObjectState.HOLLOW_PERSISTENT_NONTRANSACTIONAL)) {
			this.pm.deletePersistent(this.pm.getObjectById(
					entity.getClass(), entity.getKey()));
		} else {
			this.pm.deletePersistent(entity);
		}
		tx.commit();
		this.pm.flush();
	}
	
	/**
	 * Persists the given entitiy.
	 * @param entity
	 * @return
	 */
	public <T extends Entity> T persist(T entity) {
		final PersistenceManager pManager = PMF.get().getPersistenceManager();
		T result = null;
		try {
			result = pManager.makePersistent(entity);
			pManager.flush();
		} finally {
			pManager.close();
		}
		return result;
	}
	
	/**
	 * Finds the entity with the given key.
	 * Throws a {@link JDOObjectNotFoundException} if no object is found.
	 * @param key
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Entity> T findByKey(final Key key) {
		T result;
		try {
			result = (T) this.pm
					.getObjectById(this.getEntityClass(), key);
		} catch (JDOObjectNotFoundException e) {
			result = null;
		}
		return result;
	}
	
	/**
	 * Finds all items of this entity.
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends Entity> List<T> findAll() {
		List<T> result = (List<T>) this.pm
				.newQuery(this.getEntityClass()).execute();
		if (result == null) {
			result = new ArrayList<T>();
		}
		return result;
	}
	
	/**
	 * This function is for closing the connection and should be called after the DAO has been used.
	 */
	public void close() {
		this.pm.close();
	}
}

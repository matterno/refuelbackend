package de.timoklostermann.refuel.datastore.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import de.timoklostermann.refuel.datastore.entity.User;
import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.util.PMF;

public class UserDAO extends AbstractDAO {

	/**
	 * The Logger.
	 */
	private static final Logger LOG = Logger.getLogger(UserDAO.class.getName());
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) UserDAO.class;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByName(String name) {
		final PersistenceManager pManager = PMF.get().getPersistenceManager();
        final Query query = pManager.newQuery(User.class);
        query.setFilter("this.name == name");
        query.declareParameters("String name");
        List<User> users = null;
        try {
        	users = (List<User>) query.execute(name);
        } finally {
        	query.closeAll();
        }
        return users;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findByEmail(String email) {
		final PersistenceManager pManager = PMF.get().getPersistenceManager();
        final Query query = pManager.newQuery(User.class);
        query.setFilter("this.email == email");
        query.declareParameters("String email");
        List<User> users = null;
        try {
        	users = (List<User>) query.execute(email);
        } finally {
        	query.closeAll();
        }
        return users;
	}
}

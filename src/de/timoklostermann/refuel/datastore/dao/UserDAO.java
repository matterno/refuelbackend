package de.timoklostermann.refuel.datastore.dao;

import java.util.List;

import javax.jdo.Query;

import de.timoklostermann.refuel.datastore.entity.Entity;
import de.timoklostermann.refuel.datastore.entity.User;

public class UserDAO extends AbstractDAO {
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Entity> Class<T> getEntityClass() {
		return (Class<T>) UserDAO.class;
	}
	
	/**
	 * Finds a user by his name.
	 * @param name The user name.
	 * @return The User.
	 */
	@SuppressWarnings("unchecked")
	public User findByName(String name) {
        final Query query = pm.newQuery(User.class);
        query.setFilter("this.name == name");
        query.declareParameters("String name");
        List<User> users = null;
        try {
        	users = (List<User>) query.execute(name);
        } finally {
        	query.closeAll();
        }
        if(users.isEmpty()) {
        	return null;
        } else {
        	return users.get(0);
        }
	}
	
	/**
	 * Finds a user by his email adress.
	 * @param email The email adress.
	 * @return The User.
	 */
	@SuppressWarnings("unchecked")
	public User findByEmail(String email) {
        final Query query = pm.newQuery(User.class);
        query.setFilter("this.email == email");
        query.declareParameters("String email");
        List<User> users = null;
        try {
        	users = (List<User>) query.execute(email);
        } finally {
        	query.closeAll();
        }
        if(users.isEmpty()) {
        	return null;
        } else {
        	return users.get(0);
        }
	}
}

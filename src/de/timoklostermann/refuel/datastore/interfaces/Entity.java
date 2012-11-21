package de.timoklostermann.refuel.datastore.interfaces;

import com.google.appengine.api.datastore.Key;

/**
 * Interface for entity classes.
 * @author Timo Klostermann
 *
 */
public interface Entity {
	
	/**
	 * Getting the primary key.
	 * @return {@link Key}
	 */
	public Key getKey();
	
	/**
	 * Setting the primary key.
	 * @param key {@link Key}
	 */
	public void setKey(Key key);
}
package de.timoklostermann.datastore.interfaces;

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
	Key getPkId();
	
	/**
	 * Setting the primary key.
	 * @param pkId {@link Key}
	 */
	void setPkId(Key pkId);
}

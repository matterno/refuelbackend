package de.timoklostermann.datastore.interfaces;

import com.google.appengine.labs.repackaged.org.json.JSONObject;

public interface JSONConverter {

	/**
	 * Converting the entity to a {@link JSON} object.
	 * @return {@link JSON}
	 */
	public JSONObject toJSON();
}

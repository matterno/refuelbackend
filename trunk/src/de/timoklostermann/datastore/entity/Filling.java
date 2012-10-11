package de.timoklostermann.datastore.entity;

import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Persistent;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.common.Constants;
import de.timoklostermann.datastore.interfaces.Entity;
import de.timoklostermann.datastore.interfaces.JSONConverter;

public class Filling implements Entity, JSONConverter {

	private static final Logger LOG = Logger.getLogger("Filling");
	
	private static final String F_KEY = "F1";
	/**
	 * The primary key.
	 */
	private Key pkId;
	
	@Override
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	public Key getPkId() {
		return this.pkId;
	}

	@Override
	public void setPkId(Key pkId) {
		this.pkId = pkId;
	}

	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		try {
			obj.put(F_KEY, KeyFactory.keyToString(pkId));
		} catch (JSONException e) {
			LOG.warning(Constants.JSON_EXCEPTION_OCCURED + e.getMessage());
		}
		return obj;
	}
}

package de.timoklostermann.refuel.datastore.entity;

import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.datastore.interfaces.JSONConvertible;

@PersistenceCapable
public class Accessory implements Entity, JSONConvertible {

	/**
	 * The logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(Accessory.class.getName());
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key pkId;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String type;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private Vehicle vehicle;
	
	/* --------------------------------
	 * JSON-Keys
	 * -------------------------------- */
	
	private static final String A_ID = "A1";
	
	private static final String A_TYPE = "A2";
	
	private static final String A_VEHICLE = "A3";
	
	/* --------------------------------
	 * Getters and Setters
	 * -------------------------------- */
	
	@Override
	public Key getPkId() {
		return this.pkId;
	}

	@Override
	public void setPkId(Key pkId) {
		this.pkId = pkId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	//TODO implementieren
	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}

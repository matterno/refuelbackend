package de.timoklostermann.refuel.datastore.entity;

import java.util.Date;
import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.common.Constants;
import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.datastore.interfaces.JSONConvertible;

@PersistenceCapable
public class Filling implements Entity, JSONConvertible {

	/**
	 * The logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(Filling.class.getName());
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key pkId;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private Vehicle vehicle;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private double price;

	@Persistent(nullValue=NullValue.EXCEPTION)
	private double quantity;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int odometer;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private boolean isFilledToTop;
	
	@Persistent
	private String comment;
	
	@Persistent
	private int fuelType;
	
	@Persistent
	private Date refillDate;
	
	/* --------------------------------
	 * JSON-Keys
	 * -------------------------------- */
	
	/**
	 * Json-Key for pkId.
	 */
	private static final String F_ID = "F1";
	
	private static final String F_VEHICLE = "F2";
	
	private static final String F_PRICE = "F3";
	
	private static final String F_QUANTITY = "F4";
	
	private static final String F_ODOMETER = "F5";
	
	private static final String F_FILLEDTOTOP = "F6";
	
	private static final String F_COMMENT = "F7";
	
	private static final String F_FUELTYPE = "F8";
	
	private static final String F_REFILLDATE = "F9";
	
	/* --------------------------------
	 * Constructors
	 * -------------------------------- */
	
	public Filling() {}
	
	//TODO
	public Filling(JSONObject obj) {
		try{
			if(obj.has(F_ID))
				this.setPkId(KeyFactory.stringToKey(obj.getString(F_ID)));
			//TODO Get Vehicle from DB
		    //if(obj.has(F_VEHICLE))
	        //    this.setCar(obj.getString(F_VEHICLE));
			if(obj.has(F_PRICE))
				this.setPrice(Double.parseDouble(obj.getString(F_PRICE)));
   	 	} catch(JSONException je){
   	 		LOG.warning(Constants.JSON_EXCEPTION_OCCURED + je.getMessage());
 		}
	}
	
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

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public int getOdometer() {
		return odometer;
	}

	public void setOdometer(int odometer) {
		this.odometer = odometer;
	}

	public boolean isFilledToTop() {
		return isFilledToTop;
	}

	public void setFilledToTop(boolean isFilledToTop) {
		this.isFilledToTop = isFilledToTop;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getFuelType() {
		return fuelType;
	}

	public void setFuelType(int fuelType) {
		this.fuelType = fuelType;
	}

	public Date getRefillDate() {
		return refillDate;
	}

	public void setRefillDate(Date refillDate) {
		this.refillDate = refillDate;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public JSONObject toJSON() {
		JSONObject obj = new JSONObject();
		
		try {
			if(this.pkId != null) {
				obj.put(F_ID, KeyFactory.keyToString(pkId));
			}
			obj.put(F_VEHICLE, this.vehicle.getPkId());
			
			//TODO
		} catch (JSONException e) {
			LOG.warning(Constants.JSON_EXCEPTION_OCCURED + e.getMessage());
		}
		return obj;
	}
}

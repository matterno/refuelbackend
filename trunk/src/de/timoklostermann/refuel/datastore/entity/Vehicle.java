package de.timoklostermann.refuel.datastore.entity;

import java.util.List;
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
public class Vehicle implements Entity, JSONConvertible {

	/**
	 * The logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(Vehicle.class.getName());
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key pkId;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private User user;
	
	@Persistent
	private String marque;
	
	@Persistent
	private String type;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String name;
	
	@Persistent
	private int buildYear;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int vehicleType;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int distanceUnit;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int quantityUnit;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int consumptionUnit;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int currency;
	
	@Persistent(mappedBy = "vehicle")
	private List<Filling> fillings;
	
	@Persistent(mappedBy = "vehicle")
	private List<Accessory> accessories;
	
	/* --------------------------------
	 * JSON-Keys
	 * -------------------------------- */
	
	private static final String V_ID = "V1";
	
	private static final String V_USER = "V2";
	
	private static final String V_MARQUE = "V3";
	
	private static final String V_TYPE = "V4";
	
	private static final String V_NAME = "V5";
	
	private static final String V_BUILDYEAR = "V6";
	
	private static final String V_VEHICLETYPE = "V7";
	
	private static final String V_DISTANCEUNIT = "V8";
	
	private static final String V_QUANTITYUNIT = "V9";
	
	private static final String V_CONSUMPTIONUNIT = "V10";
	
	private static final String V_CURRENCY = "V11";
	
	private static final String V_FILLINGS = "V12";
	
	private static final String V_ACCESSORIES = "V13";
	
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

	
	public String getMarque() {
		return marque;
	}

	public void setMarque(String marque) {
		this.marque = marque;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getBuildYear() {
		return buildYear;
	}

	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getDistanceUnit() {
		return distanceUnit;
	}

	public void setDistanceUnit(int distanceUnit) {
		this.distanceUnit = distanceUnit;
	}

	public int getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(int quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public int getConsumptionUnit() {
		return consumptionUnit;
	}

	public void setConsumptionUnit(int consumptionUnit) {
		this.consumptionUnit = consumptionUnit;
	}

	public int getCurrency() {
		return currency;
	}

	public void setCurrency(int currency) {
		this.currency = currency;
	}

	public List<Filling> getFillings() {
		return fillings;
	}

	public void setFillings(List<Filling> fillings) {
		this.fillings = fillings;
	}

	//TODO implementieren
	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}
}

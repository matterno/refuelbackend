package de.timoklostermann.refuel.datastore.entity;

import java.util.Set;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Vehicle implements Entity, Comparable<Vehicle> {
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
	@Persistent
	private String make;
	
	@Persistent
	private String model;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String name;
	
	@Persistent
	private int buildYear;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int vehicleTypeID;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int distanceUnitID;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int quantityUnitID;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private int consumptionUnitID;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String currency;
	
	@Persistent
	private Set<Filling> fillings; 
	
	/* --------------------------------
	 * Constructor
	 * -------------------------------- */
	
	/**
	 * Constructor for all needed (not null) information.
	 * @param user
	 * @param name
	 * @param vehicleTypeID
	 * @param distanceUnitID
	 * @param quantityUnitID
	 * @param consumptionUnitID
	 * @param currency
	 */
	public Vehicle(String name, int vehicleTypeID, int distanceUnitID, int quantityUnitID, int consumptionUnitID, String currency) {
		this.name = name;
		this.vehicleTypeID = vehicleTypeID;
		this.distanceUnitID = distanceUnitID;
		this.quantityUnitID = quantityUnitID;
		this.consumptionUnitID = consumptionUnitID;
		this.currency = currency;
	}
	
	/* --------------------------------
	 * Getters and Setters
	 * -------------------------------- */
	
	public Key getKey() {
		return this.key;
	}

	public void setKey(Key key) {
		this.key = key;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
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

	public int getVehicleTypeID() {
		return vehicleTypeID;
	}

	public void setVehicleTypeID(int vehicleTypeID) {
		this.vehicleTypeID = vehicleTypeID;
	}

	public int getDistanceUnitID() {
		return distanceUnitID;
	}

	public void setDistanceUnitID(int distanceUnitID) {
		this.distanceUnitID = distanceUnitID;
	}

	public int getQuantityUnitID() {
		return quantityUnitID;
	}

	public void setQuantityUnitID(int quantityUnitID) {
		this.quantityUnitID = quantityUnitID;
	}

	public int getConsumptionUnitID() {
		return consumptionUnitID;
	}

	public void setConsumptionUnitID(int consumptionUnitID) {
		this.consumptionUnitID = consumptionUnitID;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Set<Filling> getFillings() {
		return fillings;
	}

	public void setFillings(Set<Filling> fillings) {
		this.fillings = fillings;
	}

	@Override
	public int compareTo(Vehicle o) {
		if(key.getId() < o.getKey().getId()) {
			return -1;
		} else {
			return 1;
		}
	}
}

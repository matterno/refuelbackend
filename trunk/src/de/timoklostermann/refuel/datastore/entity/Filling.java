package de.timoklostermann.refuel.datastore.entity;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class Filling implements Entity {
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;
	
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
	 * Getters and Setters
	 * -------------------------------- */
	
	public Key getKey() {
		return this.key;
	}

	public void setKey(Key key) {
		this.key = key;
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
}

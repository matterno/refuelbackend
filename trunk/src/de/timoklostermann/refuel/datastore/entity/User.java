package de.timoklostermann.refuel.datastore.entity;

import java.util.Date;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;


@PersistenceCapable
public class User implements Entity {
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent(nullValue=NullValue.EXCEPTION)
	private String name;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String password;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String email;
	
	@Persistent
	private Date lastLoginDate;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private Date createDate;
	
	@Persistent
	private List<Vehicle> vehicles;
	
	/* --------------------------------
	 * Constructor
	 * -------------------------------- */
	
	/**
	 * The constructor with all needed information.
	 * @param nick
	 * @param password
	 * @param email
	 * @param createDate
	 */
	public User(String nick, String password, String email, Date createDate) {
		this.name = nick;
		this.password = password;
		this.email = email;
		this.createDate = createDate;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
}

package de.timoklostermann.refuel.datastore.entity;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.NullValue;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.interfaces.Entity;
import de.timoklostermann.refuel.datastore.interfaces.JSONConvertible;

@PersistenceCapable
public class User implements Entity, JSONConvertible {

	/**
	 * The logging instance.
	 */
	private static final Logger LOG = Logger.getLogger(User.class.getName());
	
	/* --------------------------------
	 * Attributes
	 * -------------------------------- */
	
	@PrimaryKey
	@Persistent(valueStrategy=IdGeneratorStrategy.IDENTITY)
	private Key pkId;

	@Unique
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String name;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String password;
	
	@Unique
	@Persistent(nullValue=NullValue.EXCEPTION)
	private String email;
	
	@Persistent
	private Date lastLoginDate;
	
	@Persistent(nullValue=NullValue.EXCEPTION)
	private Date createDate;
	
	@Persistent(mappedBy="user")
	private List<Vehicle> vehicles;
	
	/* --------------------------------
	 * JSON-Keys
	 * -------------------------------- */
	
	private static final String U_ID = "U1";
	
	private static final String U_NICK = "U2";
	
	private static final String U_PW = "U3";
	
	private static final String U_EMAIL = "U4";
	
	private static final String U_LASTLOGIN = "U5";
	
	private static final String U_CREATEDATE = "U6";
	
	private static final String U_VEHICLES = "U7";
	
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
	
	@Override
	public Key getPkId() {
		return this.pkId;
	}
	
	@Override
	public void setPkId(Key pkId) {
		this.pkId = pkId;
	}
	
	public String getNick() {
		return name;
	}

	public void setNick(String nick) {
		this.name = nick;
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

	@Override
	public JSONObject toJSON() {
		// TODO Auto-generated method stub
		return null;
	}



}

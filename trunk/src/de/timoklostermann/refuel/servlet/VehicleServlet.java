package de.timoklostermann.refuel.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.dao.UserDAO;
import de.timoklostermann.refuel.datastore.entity.User;
import de.timoklostermann.refuel.datastore.entity.Vehicle;
import de.timoklostermann.refuel.util.Constants;

@SuppressWarnings("serial")
public class VehicleServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(RegisterServlet.class
			.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		int requestType = Integer.parseInt(req
				.getParameter(Constants.REQUEST_TYPE));

		String userName = req.getParameter(Constants.USER_NAME);
		String vehicleMake = req.getParameter(Constants.VEHICLE_MAKE);
		String vehicleModel = req.getParameter(Constants.VEHICLE_MODEL);
		String vehicleName = req.getParameter(Constants.VEHICLE_NAME);
		int vehicleYear = (req.getParameter(Constants.VEHICLE_YEAR) != null ? Integer
				.parseInt(req.getParameter(Constants.VEHICLE_YEAR)) : 0);
		int vehicleType = (req.getParameter(Constants.VEHICLE_TYPE_ID) != null ? Integer
				.parseInt(req.getParameter(Constants.VEHICLE_TYPE_ID)) : 0);
		int vehicleDistanceUnit = (req
				.getParameter(Constants.VEHICLE_DISTANCE_UNIT) != null ? Integer
				.parseInt(req.getParameter(Constants.VEHICLE_DISTANCE_UNIT))
				: 0);
		int vehicleQuantityUnit = (req
				.getParameter(Constants.VEHICLE_QUANTITY_UNIT) != null ? Integer
				.parseInt(req.getParameter(Constants.VEHICLE_QUANTITY_UNIT))
				: 0);
		int vehicleConsumptionUnit = (req
				.getParameter(Constants.VEHICLE_CONSUMPTION_UNIT) != null ? Integer
				.parseInt(req.getParameter(Constants.VEHICLE_CONSUMPTION_UNIT))
				: 0);
		String vehicleCurrency = req.getParameter(Constants.VEHICLE_CURRENCY);
		long vehicleID = (req.getParameter(Constants.VEHICLE_KEY) != null ? Long
				.parseLong(req.getParameter(Constants.VEHICLE_KEY)) : 0);

		log.info("RequestType: " + requestType);
		log.info("VehicleName: " + vehicleName);
		log.info("UserName: " + userName);

		JSONObject json = new JSONObject();

		try {
			switch (requestType) {
			case Constants.REQUEST_TYPE_VEHICLE_SAVE:
				this.saveVehicle(json, userName, vehicleName, vehicleType,
						vehicleDistanceUnit, vehicleQuantityUnit,
						vehicleConsumptionUnit, vehicleCurrency, vehicleMake,
						vehicleModel, vehicleYear);

				break;
			case Constants.REQUEST_TYPE_VEHICLE_GET:
				this.getVehicle(json, userName, vehicleName);
				break;
			case Constants.REQUEST_TYPE_VEHICLE_GET_ALL_LIST:
				this.getAllVehicle(json, userName);
				break;
			case Constants.REQUEST_TYPE_VEHICLE_UPDATE:
				this.updateVehicle(json, vehicleID, userName, vehicleName,
						vehicleType, vehicleDistanceUnit, vehicleQuantityUnit,
						vehicleConsumptionUnit, vehicleCurrency, vehicleMake,
						vehicleModel, vehicleYear);
				break;
			}
			json.put(Constants.REQUEST_TYPE, requestType);
		} catch (JSONException e) {
			log.severe("JSON error!");
		}

		resp.setContentType("application/json");
		resp.getWriter().print(json);
	}

	private void saveVehicle(JSONObject json, String userName,
			String vehicleName, int vehicleType, int vehicleDistanceUnit,
			int vehicleQuantityUnit, int vehicleConsumptionUnit,
			String vehicleCurrency, String vehicleMake, String vehicleModel,
			int vehicleYear) throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(userName);

		// Create new entity
		Vehicle vehicle = new Vehicle(vehicleName, vehicleType,
				vehicleDistanceUnit, vehicleQuantityUnit,
				vehicleConsumptionUnit, vehicleCurrency);
		vehicle.setMake(vehicleMake);
		vehicle.setModel(vehicleModel);
		vehicle.setBuildYear(vehicleYear);

		// Get all vehicles
		Set<Vehicle> vehicles = user.getVehicles();
		if (vehicles == null) {
			vehicles = new HashSet<Vehicle>();
		}

		// Checking if vehicle with same name exists
		for (Vehicle v : vehicles) {
			if (vehicleName.equals(v.getName())) {
				json.put(Constants.JSON_SUCCESS, false);
				json.put(Constants.JSON_ERROR, Constants.ERROR_VEHICLE_EXISTS);
				userDAO.close();
				return;
			}
		}

		// Adding vehicle to list
		vehicles.add(vehicle);

		// Setting new vehicle list to user
		user.setVehicles(vehicles);
		userDAO.close();

		// Return success
		json.put(Constants.JSON_SUCCESS, true);
	}

	private void getVehicle(JSONObject json, String userName, String vehicleName)
			throws JSONException {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(userName);

		Set<Vehicle> vehicles = user.getVehicles();
		userDAO.close();

		Vehicle vehicle = null;
		for (Vehicle v : vehicles) {
			if (v.getName().equals(vehicleName)) {
				vehicle = v;
				break;
			}
		}

		if (vehicle == null) {
			json.put(Constants.JSON_SUCCESS, false);
			json.put(Constants.JSON_ERROR, Constants.ERROR_VEHICLE_EXISTS_NOT);
		} else {
			json.put(Constants.JSON_SUCCESS, true);
			json.put(Constants.VEHICLE_MAKE, vehicle.getMake());
			json.put(Constants.VEHICLE_MODEL, vehicle.getModel());
			json.put(Constants.VEHICLE_NAME, vehicle.getName());
			json.put(Constants.VEHICLE_YEAR, vehicle.getBuildYear());
			json.put(Constants.VEHICLE_TYPE_ID, vehicle.getVehicleTypeID());
			json.put(Constants.VEHICLE_DISTANCE_UNIT,
					vehicle.getDistanceUnitID());
			json.put(Constants.VEHICLE_QUANTITY_UNIT,
					vehicle.getQuantityUnitID());
			json.put(Constants.VEHICLE_CONSUMPTION_UNIT,
					vehicle.getConsumptionUnitID());
			json.put(Constants.VEHICLE_CURRENCY, vehicle.getCurrency());
			json.put(Constants.VEHICLE_KEY, vehicle.getKey().getId());
		}

	}

	private void getAllVehicle(JSONObject json, String userName)
			throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(userName);

		if (user == null) {
			json.put(Constants.JSON_SUCCESS, false);
			json.put(Constants.JSON_ERROR, Constants.ERROR_USER_EXISTS_NOT);
			return;
		}

		Set<Vehicle> vehicles = user.getVehicles();
		userDAO.close();

		if (vehicles == null) {
			vehicles = new HashSet<Vehicle>();
		}

		json.put(Constants.JSON_SUCCESS, true);

		JSONArray vehicleNames = new JSONArray();
		for (Vehicle v : vehicles) {
			vehicleNames.put(v.getName());
		}

		json.put(Constants.VEHICLE_NAMES, vehicleNames);

	}

	private void updateVehicle(JSONObject json, long vehicleId,
			String userName, String vehicleName, int vehicleType,
			int vehicleDistanceUnit, int vehicleQuantityUnit,
			int vehicleConsumptionUnit, String vehicleCurrency,
			String vehicleMake, String vehicleModel, int vehicleYear)
			throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(userName);

		Set<Vehicle> vehicles = user.getVehicles();
		if (vehicles == null) {
			userDAO.close();
			json.put(Constants.JSON_SUCCESS, false);
			json.put(Constants.JSON_ERROR, Constants.ERROR_VEHICLE_EXISTS_NOT);
			return;
		}

		Vehicle vehicle = null;
		for (Vehicle v : vehicles) {
			if (v.getKey().getId() == (vehicleId)) {
				vehicle = v;
				break;
			}
		}
		if (vehicle == null) {
			userDAO.close();
			json.put(Constants.JSON_SUCCESS, false);
			json.put(Constants.JSON_ERROR, Constants.ERROR_VEHICLE_EXISTS_NOT);
			return;
		}

		vehicle.setName(vehicleName);
		vehicle.setVehicleTypeID(vehicleType);
		vehicle.setDistanceUnitID(vehicleDistanceUnit);
		vehicle.setQuantityUnitID(vehicleQuantityUnit);
		vehicle.setConsumptionUnitID(vehicleConsumptionUnit);
		vehicle.setCurrency(vehicleCurrency);
		vehicle.setMake(vehicleMake);
		vehicle.setModel(vehicleModel);
		vehicle.setBuildYear(vehicleYear);

		userDAO.close();

		json.put(Constants.JSON_SUCCESS, true);
	}
}
package de.timoklostermann.refuel.servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.labs.repackaged.org.json.JSONArray;
import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.dao.UserDAO;
import de.timoklostermann.refuel.datastore.dao.VehicleDAO;
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

		int requestType = Integer.parseInt(req.getParameter(Constants.REQUEST_TYPE));

		//TODO Instanzierung in switch-case verlagern
		
		String vehicleUser = req.getParameter(Constants.VEHICLE_USER);
		String vehicleMake = req.getParameter(Constants.VEHICLE_MAKE);
		String vehicleModel = req.getParameter(Constants.VEHICLE_MODEL);
		String vehicleName = req.getParameter(Constants.VEHICLE_NAME);
		int vehicleYear = (req.getParameter(Constants.VEHICLE_YEAR) != null ? Integer.parseInt(req.getParameter(Constants.VEHICLE_YEAR)) : 0);
		int vehicleType = (req.getParameter(Constants.VEHICLE_TYPE_ID) != null ? Integer.parseInt(req.getParameter(Constants.VEHICLE_TYPE_ID)) : 0);
		int vehicleDistanceUnit = (req.getParameter(Constants.VEHICLE_DISTANCE_UNIT) != null ? Integer.parseInt(req.getParameter(Constants.VEHICLE_DISTANCE_UNIT)) : 0);
		int vehicleQuantityUnit = (req.getParameter(Constants.VEHICLE_QUANTITY_UNIT) != null ? Integer.parseInt(req.getParameter(Constants.VEHICLE_QUANTITY_UNIT)) : 0);
		int vehicleConsumptionUnit = (req.getParameter(Constants.VEHICLE_CONSUMPTION_UNIT) != null ? Integer.parseInt(req.getParameter(Constants.VEHICLE_CONSUMPTION_UNIT)) : 0);
		String vehicleCurrency = req.getParameter(Constants.VEHICLE_CURRENCY);

		JSONObject json = new JSONObject();

		try {
			switch (requestType) {
			case Constants.REQUEST_TYPE_VEHICLE_SAVE_DEFAULT:
				this.saveDefaultVehicle(json, vehicleUser, vehicleName,
						vehicleType, vehicleDistanceUnit, vehicleQuantityUnit,
						vehicleConsumptionUnit, vehicleCurrency, vehicleMake,
						vehicleModel, vehicleYear);

				break;
			case Constants.REQUEST_TYPE_VEHICLE_GET_DEFAULT:
				this.getDefaultVehicle(json, vehicleUser);
			case Constants.REQUEST_TYPE_VEHICLE_GET_ALL_LIST:
				this.getAllVehicle(json, vehicleUser);
			}
		} catch (JSONException e) {
			log.severe("JSON error!");
		}
		
		resp.setContentType("application/json");
		resp.getWriter().print(json);
	}

	private void saveDefaultVehicle(JSONObject json, String vehicleUser,
			String vehicleName, int vehicleType, int vehicleDistanceUnit,
			int vehicleQuantityUnit, int vehicleConsumptionUnit,
			String vehicleCurrency, String vehicleMake, String vehicleModel,
			int vehicleYear) throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(vehicleUser);
		userDAO.close();

		// Create new entity
		VehicleDAO vehicleDAO = new VehicleDAO();
		Vehicle vehicle = new Vehicle(user, vehicleName, vehicleType,
				vehicleDistanceUnit, vehicleQuantityUnit,
				vehicleConsumptionUnit, vehicleCurrency);
		vehicle.setMake(vehicleMake);
		vehicle.setModel(vehicleModel);
		vehicle.setBuildYear(vehicleYear);

		vehicle = vehicleDAO.saveOrUpdate(vehicle);
		vehicleDAO.close();

		// set default vehicle for user
		user.setDefaultVehicle(vehicle);
		userDAO.persist(user);

		json.put(Constants.JSON_SUCCESS, true);

	}

	private void getDefaultVehicle(JSONObject json, String vehicleUser)
			throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(vehicleUser);

		Vehicle vehicle = user.getDefaultVehicle();
		userDAO.close();

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
			json.put(Constants.VEHICLE_DISTANCE_UNIT, vehicle.getDistanceUnitID());
			json.put(Constants.VEHICLE_QUANTITY_UNIT, vehicle.getQuantityUnitID());
			json.put(Constants.VEHICLE_CONSUMPTION_UNIT, vehicle.getConsumptionUnitID());
			json.put(Constants.VEHICLE_CURRENCY, vehicle.getCurrency());
		}
	}

	private void getAllVehicle(JSONObject json, String vehicleUser) throws JSONException {
		// Find user in DB
		UserDAO userDAO = new UserDAO();
		User user = userDAO.findByName(vehicleUser);
		userDAO.close();
		
		VehicleDAO vehicleDAO = new VehicleDAO();
		List<Vehicle> vehicles = vehicleDAO.findByUser(user);
		
		if(vehicles == null || vehicles.isEmpty()) {
			json.put(Constants.JSON_SUCCESS, false);
			json.put(Constants.JSON_ERROR, Constants.ERROR_VEHICLE_EXISTS_NOT);
		} else {
			json.put(Constants.JSON_SUCCESS, true);
			
			JSONArray jsonArray = new JSONArray();
			for(Vehicle v : vehicles) {
				jsonArray.put(v.getName());
			}
			
			json.put(Constants.VEHICLE_NAMES, jsonArray);
		}
		vehicleDAO.close();
	}
}

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
public class FillingServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(FillingServlet.class
			.getName());

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO
	}
}

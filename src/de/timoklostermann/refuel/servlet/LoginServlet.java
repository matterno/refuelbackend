package de.timoklostermann.refuel.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.dao.UserDAO;
import de.timoklostermann.refuel.util.Constants;

@SuppressWarnings("serial")
public class LoginServlet extends HttpServlet {

	private static final Logger log = Logger.getLogger(LoginServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		JSONObject json = new JSONObject();

		String loginName = req.getParameter(Constants.LOGIN_NAME);
		String loginPw = req.getParameter(Constants.LOGIN_PASSWORD);

		// Set the response content type to json.
		resp.setContentType("application/json");
		
		UserDAO userDAO = new UserDAO();

		try {
			// JDO for AppEngine doesnt support @unique annotations. Looking for
			// entries with given name or email.
			if (userDAO.findByName(loginName) == null) {
				// User not found
				log.info("User " + loginName + " not found");
				json.put(Constants.JSON_SUCCESS, false);
				json.put(Constants.JSON_ERROR, Constants.ERROR_USER_EXISTS_NOT);
			} else if(userDAO.findByName(loginName).getPassword().equals(loginPw)) {
				// PW is the same
				log.info("User " + loginName + " logged in");
				userDAO.findByName(loginName).setLastLoginDate(new Date());
				json.put(Constants.JSON_SUCCESS, true);
			} else {
				// PW Wrong
				log.info("Wrong password for " + loginName);
				json.put(Constants.JSON_SUCCESS, false);
				json.put(Constants.JSON_ERROR, Constants.ERROR_PW_WRONG);
			}
			userDAO.close();
		} catch (JSONException e) {
			log.severe("JSON error!");
		}
		
		resp.getWriter().print(json);
	}
}

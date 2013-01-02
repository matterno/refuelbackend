package de.timoklostermann.refuel.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

import de.timoklostermann.refuel.datastore.dao.UserDAO;
import de.timoklostermann.refuel.datastore.entity.User;
import de.timoklostermann.refuel.util.Constants;

@SuppressWarnings("serial")
public class RegisterServlet extends HttpServlet {
	
	private static final Logger log = Logger.getLogger(RegisterServlet.class.getName());
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String registerName = req.getParameter(Constants.REGISTER_NAME);
		String registerEmail = req.getParameter(Constants.REGISTER_EMAIL);
		String registerPw = req.getParameter(Constants.REGISTER_PW);

		JSONObject json = new JSONObject();
		resp.setContentType("application/json");

		UserDAO userDAO = new UserDAO();

		try {
			// JDO for AppEngine doesnt support @unique annotations. Looking for
			// entries with given name or email.
			if (userDAO.findByName(registerName) == null
					&& userDAO.findByEmail(registerEmail) == null) {
				// no other entry found
				
				User user = new User(registerName, registerPw, registerEmail, new Date());
				user.setLastLoginDate(new Date());
				userDAO.persist(user);

				json.put(Constants.JSON_SUCCESS, true);

			} else {
				// Entry found. User is already registered!
				
				json.put(Constants.JSON_SUCCESS, false);
				json.put(Constants.JSON_ERROR, Constants.ERROR_USER_EXISTS);
				// TODO ErrorCode zurück
			}
			userDAO.close();
		} catch (JSONException e) {
			log.severe("JSON error!");
		}

		resp.getWriter().print(json);
	}
}

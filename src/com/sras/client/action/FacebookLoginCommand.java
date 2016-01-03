package com.sras.client.action;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.velocity.context.Context;

import com.google.gson.Gson;
import com.sras.client.action.LoginCommand.LoginType;
import com.sras.client.utils.ClientConstants;
import com.sras.client.utils.Utilities;

public class FacebookLoginCommand extends Command {

	public FacebookLoginCommand(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		super(request, response, ctx);
		// TODO Auto-generated constructor stub
	}

	public String doAjaxGet() throws Exception {
		doPost();
		return TEMPLATE_NAME;
	}

	public String doPost() throws Exception {
		TEMPLATE_NAME = "index.vm";
		String type = request.getParameter("ltype");
		try {
			HttpSession session = request.getSession();
			Object userName = session.getAttribute("userName");
			if (type.equalsIgnoreCase("logout")) {
				session.invalidate();
				session = null;
			} else if (type.equalsIgnoreCase("login")) {
				String loginParameters = request
						.getParameter("loginParameters");

				// {"id":"1052698364","birthday":"11/28/1982","email":"itsras@gmail.com","first_name":"Srinivas","gender":"male",
				// "last_name":"Kummaragunta","link":"https://www.facebook.com/app_scoped_user_id/1052698364/",
				// "location":{"id":"115200305158163","name":"Hyderabad, India"},"locale":"en_US",
				// "name":"Srinivas Kummaragunta","timezone":5.5,"updated_time":"2015-11-23T01:37:00 0000","verified":true}
				Gson gson = new Gson();
				UserData user = gson.fromJson(loginParameters, UserData.class); 

				int timeOut = ClientConstants.COOKIE_AGE;
				String uuid = UUID.randomUUID().toString();
				// String encryptedUuid = Encryption.encrypt(uuid);
				Utilities.addCookie(response, ClientConstants.COOKIE_NAME,
						uuid, ClientConstants.COOKIE_AGE);
				// ClientConstants.sessions.put(uuid, user);
				timeOut = ClientConstants.PERSISTANCE_COOKIE_AGE;
				setLoginAttributes(session, request, user, uuid,
						LoginType.FULL_AUTHENTICATION.toString());
				// SessionHelper.createUserSession(request, uuid,
				// user.getId(),timeOut);s
				log.debug("After Login UUID ::" + uuid);
			} else if (type.equalsIgnoreCase("logout")) {
				session.invalidate();
				session = null;
			} else if (userName != null) {

			}
		} catch (Exception e) {
			log.debug(e);
		}
		return TEMPLATE_NAME;
	}

	public static void setLoginAttributes(HttpSession session,
			HttpServletRequest request, UserData user, String uuid,
			String loginType) throws Exception

	{
		String userName = user.getName();
		session.setAttribute("userName", userName);
		session.setAttribute("userId", user.getId());
		session.setAttribute("user", user);
		session.setAttribute("uuid", uuid);
		session.setAttribute(ClientConstants.LoginType, loginType);
		// session.setAttribute("password", password);

		log.info("User " + userName + " logged in from "
				+ Utilities.getRemoteHostName(request));
		System.setProperty("adminDefaultLoginName", userName);
	}
}

class UserData implements Serializable {
	String name;
	long id;
	String birthday;
	String email;
	String first_name;
	String last_name;
	String gender;

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}

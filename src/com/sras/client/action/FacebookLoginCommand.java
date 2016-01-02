package com.sras.client.action;

import java.io.Serializable;
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

			if (type.equalsIgnoreCase("login")) {
				String loginParameters = request
						.getParameter("loginParameters");
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
			} else {
				session.invalidate();
				session = null;
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

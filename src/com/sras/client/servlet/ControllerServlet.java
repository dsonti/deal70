package com.sras.client.servlet;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.net.SocketException;
import java.util.Hashtable;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Category;
import org.apache.log4j.Level;
import org.apache.log4j.PropertyConfigurator;
import org.apache.velocity.Template;
import org.apache.velocity.context.Context;
import org.apache.velocity.exception.MethodInvocationException;
import org.apache.velocity.exception.ParseErrorException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.apache.velocity.servlet.VelocityServlet;

import com.google.appengine.api.utils.SystemProperty;
import com.google.gson.Gson;
import com.sras.client.action.CategoryCommand;
import com.sras.client.action.Command;
import com.sras.client.action.StoreCommand;
import com.sras.client.utils.ClientConstants;
import com.sras.client.utils.Formatter;
import com.sras.client.utils.SessionHelper;
import com.sras.client.utils.Utilities;
import com.sras.datamodel.UserData;

@SuppressWarnings("deprecation")
public class ControllerServlet extends VelocityServlet {
	private static Category log = Category.getInstance(ControllerServlet.class);
	private static Integer SESSION_TIMEOUT = null;
	private static final long serialVersionUID = 1L;
	private static final Properties actionMappings = new Properties();
	private static final String FRAMEWORK_TEMPLATE = "framework.vm";
	private static final String ERROR_TEMPLATE = "error.vm";

	/**
	 * Get the deployment directory for Jetty.
	 * 
	 * @return deploy directory name
	 */
	public String getDeployDirectory() {
		ServletContext servletContext = getServletContext();
		String realPath = servletContext.getRealPath("./");
		return realPath;
	}

	private void loadActionMappings(ServletConfig config)
			throws FileNotFoundException, IOException {
		if (actionMappings.size() == 0) {
			String actionMappingsFile = config
					.getInitParameter("action.mappings");
			if (actionMappingsFile != null) {
				String realPath = getServletContext().getRealPath(
						actionMappingsFile);

				if (realPath != null) {
					actionMappingsFile = realPath;
				}
			}

			actionMappings.load(new FileInputStream(actionMappingsFile));
		}
	}

	@Override
	protected Properties loadConfiguration(ServletConfig config)
			throws IOException, FileNotFoundException {
		loadActionMappings(config);

		String propsFile = config.getInitParameter(INIT_PROPS_KEY);
		/*
		 * now convert to an absolute path relative to the webapp root This will
		 * work in a decently implemented servlet 2.2 container like Tomcat.
		 */
		if (propsFile != null) {
			String realPath = getServletContext().getRealPath(propsFile);

			if (realPath != null) {
				propsFile = realPath;
			}
		}

		Properties properties = new Properties();
		properties.load(new FileInputStream(propsFile));

		/*
		 * now, lets get the two elements we care about, the template path and
		 * the log, and fix those from relative to the webapp root, to absolute
		 * on the filesystem, which is what velocity needs
		 */

		String path = properties.getProperty("file.resource.loader.path");
		if (path != null) {
			String isRelativeToDeployDirectory = properties
					.getProperty("file.resource.loader.isRelativeToDeployDirectory");
			if (isRelativeToDeployDirectory == null
					|| !isRelativeToDeployDirectory.equalsIgnoreCase("false")) {
				path = getServletContext().getRealPath(path);
			}
			log.info("Setting file.resource.loader.path to: " + path);

			properties.setProperty("file.resource.loader.path", path);
		}
		return properties;
	}

	@Override
	protected void mergeTemplate(Template template, Context context,
			HttpServletResponse httpServletResponse)
			throws ResourceNotFoundException, ParseErrorException,
			MethodInvocationException, IOException,
			UnsupportedEncodingException, Exception {
		try {
			super.mergeTemplate(template, context, httpServletResponse);
		} catch (SocketException e) {
			log.error("Socket exception in mergeTemplate: " + e.getMessage()
					+ "\nTemplate = " + template.getName());
			return;
		} catch (EOFException e) {
			log.error("EOFException in mergeTemplate: " + e.getMessage()
					+ "\nTemplate = " + template.getName());
			return;
		} catch (Exception e) {
			final String exceptionClassName = e.getClass().getName();
			if (exceptionClassName.indexOf("ClientAbortException") >= 0) {
				log.error("ClientAbortException exception in mergeTemplate: "
						+ e + "\nTemplate = " + template.getName());
				return;
			}
			log.error("Exception in mergeTemplate: " + e + "\nTemplate = "
					+ template.getName(), e);
			throw e;
		}
	}

	@Override
	protected void requestCleanup(HttpServletRequest request,
			HttpServletResponse response, Context context) {
		super.requestCleanup(request, response, context);
		try {
			response.flushBuffer();
		} catch (SocketException e) {
			assert e == e;
			// Tomcat - ignore this exception
		} catch (IOException e) {
			boolean dontLog = false;
			// Tomcat - ignore certain exceptions
			String exceptionClassName = e.getClass().getName();
			if (exceptionClassName.indexOf("ClientAbortException") >= 0
					|| exceptionClassName.indexOf("SocketException") >= 0) {
				dontLog = true;
			}
			if (!dontLog)
				log.error(e, e);
		}
		// log.debug("requestCleanup: Flushed output");
	}

	@Override
	protected Template handleRequest(HttpServletRequest request,
			HttpServletResponse response, Context ctx) throws Exception {
		Template template = null;
		Integer sessionTimeout = null;
		final HttpSession session = request.getSession();
		long startTime = System.currentTimeMillis();

		// To handle requests like contextroot/home
		String contextPath = request.getContextPath();
		String requestURI = request.getRequestURI();
		String path = requestURI.substring(requestURI.indexOf(contextPath)
				+ contextPath.length() + 1);
		// To handle requests like contextroot/?page=home
		String page = request.getParameter(ClientConstants.pageVerb);
		if (path != null && path.trim().length() > 0
				&& !path.equalsIgnoreCase(ClientConstants.servletPage)) {
			page = path;
		}
		// This will be used in Commands to retrieve store name, category name,
		// etc (ex: store/Ebay, category/Accessories)
		ctx.put("requestURI", page);
		try {
			// Handle logout
			if (handleLogout(request, response, page, session)) {
				return null;
			}

			// Handle cookie based sign in
			validateSession(request, response, ctx, page);

			// this dynamically sets the SESSION_TIMEOUT to the default Max
			// timeout
			// interval in server.xml
			if (session != null && SESSION_TIMEOUT == null) {
				SESSION_TIMEOUT = session.getMaxInactiveInterval();
				sessionTimeout = new Integer(session.getMaxInactiveInterval());
				// don't allow timeouts while processing a request
				session.setMaxInactiveInterval(-1); // never timeout while
													// handling a request
			}

			// Handle request now...
			Boolean errorOccurred = new Boolean(false);
			template = processRequest(request, response, ctx, page,
					errorOccurred);
		} catch (Exception e) {
			log.debug(e.getMessage(), e);
			ctx.put(ClientConstants.errorTextVariableName, e.getMessage());
			System.err.println("Exception caught: " + e.getMessage());
		} finally {
			if (sessionTimeout != null) {
				Utilities.markSessionAsAccessed(session);

				// reset timeout to previous value
				session.setMaxInactiveInterval(SESSION_TIMEOUT);
			}
		}
		long stopTime = System.currentTimeMillis();
		ctx.put("elapsedTime", new Long(stopTime - startTime));
		ctx.put("startTimeServlet", new Long(startTime));
		String domainName;
		String facebookAppId;
		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
			domainName = "http://1-dot-deal70-1164.appspot.com/";
			facebookAppId = "1666864453560507";
		} else {
			domainName = ClientConstants.domainName;
			facebookAppId = "201919383155442";
		}
		ctx.put("domainName", domainName);
		ctx.put("facebookAppId", facebookAppId);
		setDefaultContextVariables(request, response, ctx);
		return template;
	}

	private void validateSession(HttpServletRequest request,
			HttpServletResponse response, Context ctx, String page)
			throws Exception, IOException, ServletException {
		HttpSession session = request.getSession();

		/*
		 * boolean noLoginRequired = pageDoesNotRequireLogin(page); String puuid
		 * = Utilities.getCookieValue(request, ClientConstants.COOKIE_NAME);
		 * String suuid = (String) session.getAttribute("uuid");
		 * 
		 * if (suuid != null && puuid == null) { UserSessionData usd =
		 * SessionHelper.getUserSession(suuid); if (usd == null &&
		 * !noLoginRequired) { removeSesstionAttributes(session);
		 * Command.redirectToLoginPage(request, response, ctx); } } else if
		 * (suuid != null && puuid != null) { UserSessionData usd =
		 * SessionHelper.getUserSession(suuid); if (usd == null &&
		 * !noLoginRequired) { removeSesstionAttributes(session);
		 * Command.redirectToLoginPage(request, response, ctx); } } else if
		 * (suuid == null && puuid != null) { log.debug("Before Login UUID ::" +
		 * puuid); UserData user = SessionHelper.getUserFromSession(puuid); if
		 * (user == null && !noLoginRequired) {
		 * removeSesstionAttributes(session); Utilities.removeCookie(response,
		 * ClientConstants.COOKIE_NAME); Command.redirectToLoginPage(request,
		 * response, ctx); } if (user != null) { SessionHelper
		 * .updateUserSession(request, puuid, user.getId(), 0);
		 * LoginCommand.setLoginAttributes(session, request, user, puuid,
		 * LoginCommand.LoginType.COOKIE_BASED_AUTHENTICATION .toString()); } }
		 * else if (suuid == null && puuid == null && !noLoginRequired) {
		 * removeSesstionAttributes(session);
		 * Command.redirectToLoginPage(request, response, ctx); }
		 */
	}

	private static void removeSesstionAttributes(HttpSession session) {
		session.setAttribute("userName", null);
		session.setAttribute("password", null);
		session.setAttribute("User", null);
		session.setAttribute("uuid", null);
	}

	@SuppressWarnings({ "unchecked" })
	private Template processRequest(HttpServletRequest request,
			HttpServletResponse response, Context ctx, String page,
			Boolean errorOccurred) throws Exception {
		final HttpSession session = request.getSession();

		// Log the request
		logTheRequest(request, session);

		page = (page == null || page.length() == 0) ? "Home" : page;

		String template = null;
		Command command = null;

		boolean dontShowInFramework = Boolean.parseBoolean(request
				.getParameter("dsif"))
				|| Boolean.parseBoolean(request.getParameter("ajax"));

		// To retrieve page when the format is like 'store/Ebay'
		String pageRoot = (page.indexOf('/') >= 0) ? page.substring(0,
				page.indexOf('/')) : page;
		String value = actionMappings.getProperty(pageRoot.toLowerCase());

		Object obj = (value != null && !value.contains(".vm")) ? Class
				.forName(value) : value;
		// Object obj = commandMap.get(page.toLowerCase());
		Class<Command> cl = null;

		if (obj != null) {
			if (obj instanceof Class) {
				cl = (Class<Command>) obj;
				if (cl != null) {
					Constructor<Command> c = cl.getDeclaredConstructor(
							HttpServletRequest.class,
							HttpServletResponse.class, Context.class);
					command = c.newInstance(request, response, ctx);
					template = command.execute();
				}
			} else if (obj instanceof String) {
				template = (String) obj;
			}
		}
		template = (template == null) ? ERROR_TEMPLATE : template;
		template = Utilities.escapeXSS(template);

		showHeader(ctx, template);

		ctx.put(ClientConstants.pageVerb, template);
		if (!dontShowInFramework) {
			ctx.put("body", template);
		}
		return dontShowInFramework ? getTemplate(template)
				: getTemplate(FRAMEWORK_TEMPLATE);
	}

	public boolean handleLogout(HttpServletRequest request,
			HttpServletResponse response, String page, final HttpSession session)
			throws IOException {
		if (page != null && page.equalsIgnoreCase("logout")) {
			synchronized (session) {
				UserData user = (UserData) session.getAttribute("user");
				if (user == null) {
					Command.redirectToURL(request, response,
							ClientConstants.servletPageWithAction + "login");
					return true;
				}
				removeSesstionAttributes(session);
				String uuid = Utilities.getCookieValue(request,
						ClientConstants.COOKIE_NAME);
				if (uuid != null) {
					try {
						SessionHelper.deleteUserSession(uuid, user.getId());
					} catch (Exception e) {
						log.error("Failed to delete the session", e);
					}
					Utilities.removeCookie(response,
							ClientConstants.COOKIE_NAME);
				} else if (session.getAttribute("uuid") != null) {
					try {
						uuid = (String) session.getAttribute("uuid");
						SessionHelper.deleteUserSession(uuid, user.getId());
					} catch (Exception e) {
						log.error("Failed to delete the session", e);
					}
				}
				session.invalidate();
			}
			Command.redirectToURL(request, response,
					ClientConstants.servletPageWithAction + "login");
			return true;
		}
		return false;
	}

	private void setDefaultContextVariables(HttpServletRequest request,
			HttpServletResponse response, Context ctx) {
		String path = new File(Thread.currentThread().getContextClassLoader()
				.getResource("").getFile()).getParentFile().getParentFile()
				.getPath();
		ctx.put("path", path);

		ctx.put("Formatter", new Formatter());
		ctx.put("Utilities", new Utilities());
		ctx.put("productName", ClientConstants.productName);
		ctx.put("productCaption", ClientConstants.productCaption);

		String contextPath = request.getContextPath();
		ctx.put("contextPath", contextPath);

		ctx.put("servletPage", ClientConstants.servletPage);
		ctx.put("actionVerb", ClientConstants.pageVerb);
		ctx.put("servletPageWithAction", contextPath + "/"
				+ ClientConstants.servletPageWithAction);
		ctx.put("servletPageWithAjaxAction", contextPath + "/"
				+ ClientConstants.servletPageWithAjaxAction);

		ctx.put("captchaPublicKey", ClientConstants.RECAPTCHA_PUBLIC_KEY);
		HttpSession session = request.getSession();
		Object userName = session.getAttribute("userName");
		if (userName != null) {
			ctx.put("userName", (String) userName);
			ctx.put("userId", session.getAttribute("userId"));
		}
		Object user = session.getAttribute("User");
		if (user != null) {
			ctx.put("user", (String) user);
		}
		String requestedURL = Utilities.getRequestedURL(request);
		ctx.put("currentURL", requestedURL);
		ctx.put("currentRequest", request);
		ctx.put("categories", new Gson().toJson(
				CategoryCommand.getCategories(), Hashtable.class));
		ctx.put("stores",
				new Gson().toJson(StoreCommand.getStores(), Hashtable.class));
		getClientIpAddr(request);
	}

	public static String getClientIpAddr(HttpServletRequest request) {
		log.info("local ipAddress:" + request.getLocalAddr());
		log.info("remote ipAddress:" + request.getRemoteAddr());

		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.info("Proxy-Client-IP:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.info("WL-Proxy-Client-IP:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
			log.info("HTTP_CLIENT_IP:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
			log.info("HTTP_X_FORWARDED_FOR:" + ip);
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
			log.info("unknown:" + ip);
		}
		return ip;
	}

	private void showHeader(Context ctx, String template) {
		boolean hideHeader = template.equalsIgnoreCase("login.vm");
		ctx.put("showHeader", !hideHeader);
	}

	private boolean pageDoesNotRequireLogin(String page) {
		if (page == null) {
			return false;
		}
		String publicPages = "login,home,store,stores,category,categories,location,copyright,logout,login,signup";
		return (publicPages.indexOf(page) >= 0);
	}

	@Override
	public void init() throws ServletException {
		super.init();
		String prefix = getServletContext().getRealPath("/");
		String file = getInitParameter("log4j-init-file");

		// if the log4j-init-file context parameter is not set, then no point in
		// trying
		if (file != null) {
			PropertyConfigurator.configure(prefix + file);
			log.info("Log4J Logging started: " + prefix + file);
		} else {
			log.warn("Log4J Is not configured for your Application: " + prefix
					+ file);
		}
	}

	private void logTheRequest(HttpServletRequest request, HttpSession session) { // log
																					// the
																					// request
		if (log.getEffectiveLevel().isGreaterOrEqual(Level.DEBUG)) {
			String requestedURL = Utilities.getRequestedURL(request);
			String method = request.getMethod();
			Object user = request.getSession().getAttribute("User");
			StringBuffer logMessage = new StringBuffer(
					"ControllerServlet.processRequest. URL = " + requestedURL);
			if (user != null) {
				logMessage.append(" user = " + user + "/");
			} else {
				logMessage.append(" from  ");
			}
			String remoteHost = Utilities.getRemoteHostName(request);
			logMessage.append(remoteHost);
			String sessionId = (session != null) ? session.getId()
					: "EMPTY_SESSION_ID";
			if (user != null) {
				logMessage.append("/");
			} else {
				logMessage.append(" session = ");
			}
			logMessage.append(sessionId);

			if (!method.equalsIgnoreCase("get")) {
				logMessage.append(" method = " + method);
			}
			log.info(logMessage);
		}
	}
}

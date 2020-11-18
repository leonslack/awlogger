package com.awto.logger.commons;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpCommunicationHandler {

	final static Logger log = LogManager.getLogger();

	private static final String CREDENTIALS_NAME = "Access-Control-Allow-Credentials";
	private static final String ORIGIN_NAME = "Access-Control-Allow-Origin";
	private static final String METHODS_NAME = "Access-Control-Allow-Methods";
	private static final String HEADERS_NAME = "Access-Control-Allow-Headers";
	private static final String HEADERS_EXPOSE = "Access-Control-Expose-Headers";
	private static final String MAX_AGE_NAME = "Access-Control-Max-Age";

	public static HttpServletResponse AddCORSHeadertoResponse(HttpServletRequest request,
			HttpServletResponse response) {
		String origin = request.getHeader("Origin");
		response.setHeader(CREDENTIALS_NAME, "true");
		if (origin != null) {
			response.setHeader(ORIGIN_NAME, origin);
		} else {
			response.setHeader(ORIGIN_NAME, "*");
		}
		response.setHeader(METHODS_NAME, "GET, OPTIONS, POST, PUT, DELETE, PATCH");
		response.setHeader(HEADERS_NAME, "Origin, X-Requested-With, Content-Type, Accept, X-Auth-Token, inPlace");
		response.setHeader(HEADERS_EXPOSE, "Origin, X-Auth-Token, inPlace");
		response.setHeader(MAX_AGE_NAME, "3600");
		return response;
	}

	public static String requestIP(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");

		if (!isIpFound(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (!isIpFound(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (!isIpFound(ipAddress)) {
			ipAddress = request.getHeader("HTTP_CLIENT_IP");
		}
		if (!isIpFound(ipAddress)) {
			ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (!isIpFound(ipAddress)) {
			ipAddress = request.getRemoteAddr();
		} else { // if ip is finally found
			ipAddress = ipAddress.split(",", 2)[0];
		}

		return ipAddress;
	}

	private static boolean isIpFound(String ip) {
		return ip != null && ip.length() > 0 && !"unknown".equalsIgnoreCase(ip);
	}

}

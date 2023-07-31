package com.mohit.Apigateway.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import java.util.function.Predicate;

@Component
public class RouteValidator {

	public static final String[] OPEN_API_ENDPOINTS = { "/auth/register", "/auth/token", "/auth/validate", "/eureka","/gettrains" };

	public Predicate<ServerHttpRequest> isSecured = request -> {
		String path = request.getPath().toString();
		for (String endpoint : OPEN_API_ENDPOINTS) {
			if (path.contains(endpoint)) {
				return false; // Endpoint does not require authorization
			}
		}
		return true; // Endpoint requires authorization
	};
}

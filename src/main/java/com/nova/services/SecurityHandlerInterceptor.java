package com.nova.services;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.nova.services.config.CredentialConfig;

@Component
public class SecurityHandlerInterceptor extends HandlerInterceptorAdapter {
	
	@Autowired
	CredentialConfig credentials;
	
	/*
	 * Simple handler method that will check to see if the client has the required secret
	 * cookie, before allowing the request to continue
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {
		
		if(request.getCookies() == null) {
			return false;
		}
		
		for(Cookie c : request.getCookies()) {
			if(c.getName().equals("t_auth")) {
				System.out.println("t_auth cookie value [" + c.getValue() + "]");
				if(c.getValue().equals(credentials.getUserSecret())) {
					return true;
				}
				
			}
		}
		response.setStatus(401);
		return false;
	}

}

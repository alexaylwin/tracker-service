package com.nova.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:credentials.properties")
@Component
public class CredentialConfig {
	
	@Value("${cloudant.password}")
	private String cloudantPassword;
	
	@Value("${cloudant.username}")
	private String cloudantUsername;
	
	@Value("${user.secret}")
	private String userSecret;
	
	@Value("${user.username}")
	private String username;
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Value("${user.password}")
	private String password;
	
	public String getCloudantUsername() {
		return this.cloudantUsername;
	}
	
	public String getUserSecret() {
		return this.userSecret;
	}
	
	public String getCloudantPassword() {
		return this.cloudantPassword;
	}

}

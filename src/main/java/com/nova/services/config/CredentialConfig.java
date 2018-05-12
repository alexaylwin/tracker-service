package com.nova.services.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:credentials.properties")
@Component
public class CredentialConfig {
	
	@Value("${cloudant.password}")
	private String cloudantPassword;
	
	@Value("${user.secret}")
	private String userSecret;
	
	public String getUserSecret() {
		return this.userSecret;
	}
	
	public String getCloudantPassword() {
		return this.cloudantPassword;
	}

}

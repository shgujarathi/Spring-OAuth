package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class ApplicationServer extends AuthorizationServerConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(ApplicationServer.class);

	@Autowired
	@Qualifier("authenticationManagerBean")
	AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		logger.info("In ClientDetailsServiceConfigurer");
		clients.inMemory().withClient("myclient").secret("secret")
				.authorizedGrantTypes("implicit", "refresh_token", "password", "authorization_code")
				.scopes("read", "write", "trust")/* .autoApprove(true) */;
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) {
		logger.info("In AuthorizationServerEndpointsConfigurer");
		authorizationServerEndpointsConfigurer.authenticationManager(authenticationManager);

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer authorizationServerSecurityConfigurer) {
		logger.info("In AuthorizationServerSecurityConfigurer");

		authorizationServerSecurityConfigurer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");

		// authorizationServerSecurityConfigurer.realm("MY_OAUTH_REALM/client");
	}

}

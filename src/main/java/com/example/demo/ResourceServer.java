package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@EnableResourceServer
public class ResourceServer extends ResourceServerConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(ResourceServer.class);

	@Override
	public void configure(HttpSecurity httpSecurity) throws Exception {
		logger.info("In HttpSecurity Method");
		/*
		 * httpSecurity.authorizeRequests().antMatchers("/user/**").permitAll().
		 * anyRequest().authenticated();
		 */

		/*
		 * httpSecurity.authorizeRequests().antMatchers("/user/**").permitAll().
		 * anyRequest().authenticated();
		 */

		/*
		 * httpSecurity.authorizeRequests().antMatchers("/").permitAll().antMatchers(
		 * "/user/**").authenticated();
		 */

		httpSecurity.authorizeRequests().antMatchers("/user/**").authenticated();
	}

}

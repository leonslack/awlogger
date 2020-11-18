package com.awto.logger.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) throws Exception {

		// Ignores anonymous resource request
		web.ignoring().antMatchers("/ping").antMatchers("/css/**.css").antMatchers("/js/**.js").antMatchers("/**.html")
				.antMatchers(HttpMethod.POST, "/forgot-password").antMatchers(HttpMethod.POST, "/ip-scan")
				.antMatchers(HttpMethod.PUT, "/forgot-password").antMatchers(HttpMethod.GET, "/confirm")
				.antMatchers(HttpMethod.GET, "/app-contents").antMatchers(HttpMethod.GET, "/connectToQuickbooks")
				.antMatchers(HttpMethod.GET, "/oauth2redirect").antMatchers(HttpMethod.POST, "/users")
				.antMatchers(HttpMethod.OPTIONS, "/**").antMatchers("/v2/api-docs", "/configuration/ui",
						"/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();

//		http.exceptionHandling().and().anonymous().and().servletApi().and().headers()
//				.cacheControl().and().and().authorizeRequests()
//
//				// Allow anonymous resource request
//				.antMatchers("/favicon.ico").permitAll().antMatchers("/**.html").permitAll().antMatchers("/css/**.css")
//				.permitAll().antMatchers("/js/**.js").permitAll()
//
//				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll();

	}

}

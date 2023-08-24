package com.example.demo.configration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.filter.Jwt_Filter;
import com.example.demo.service.UserService;

@Service
@EnableWebSecurity
public class Configure {
	
//	@Autowired
//	@Qualifier("httpSecurity")
//	private HttpSecurity http;
	@Autowired
	private Jwt_Filter authFilter;

	
	@Bean
	public UserDetailsService userDetails() {
		return new UserService();
	}
	
	
//	@Bean
//	public HttpSecurity secure(HttpSecurity http) throws Exception {
//		
//		http.cors().disable().csrf().disable().authorizeHttpRequests((authorize)->authorize.
//				requestMatchers("api/auth/**").permitAll()
//				.requestMatchers("api/**").authenticated()
//				).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authenticationProvider(authProvider()).build();
//		
//		return http;
//		
//	}
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception {

		return http.csrf().disable().cors().and()
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("api/auth/**")
						.permitAll()
						.requestMatchers("api/operations/**")
						.authenticated()

				).sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authenticationProvider(authProvider()).addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
				.build();
		// ).formLogin().and().build();
	}
	
	@Bean
	public AuthenticationProvider authProvider() {

		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetails());
		auth.setPasswordEncoder(PasswordEncoderr());

		return auth;

	}
	
	@Bean
	public PasswordEncoder PasswordEncoderr() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager auth(AuthenticationConfiguration config) throws Exception {
		// return config.getAuthenticationManager();
		return config.getAuthenticationManager();
	}
}

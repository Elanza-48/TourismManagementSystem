package com.elanza48.TMS.security;

import com.elanza48.TMS.controller.filter.JwtRequestFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
	prePostEnabled = true,
	securedEnabled = true,
	jsr250Enabled = true
)
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtRequestFilter jwtRequestFilter;
	 
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
			.and()
				.authorizeRequests()
					.antMatchers("/user/*/*").hasAnyAuthority(
							"USER",
							"MANAGER",
							"ADMIN")
					.antMatchers(HttpMethod.POST, "/user").permitAll()
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/tourPackages").permitAll()
					.antMatchers(HttpMethod.POST,"/tourPackages").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PUT,"/tourPackages/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PATCH,"/tourPackages/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.DELETE,"/tourPackages/*").hasAnyAuthority(
						"ADMIN"
					)
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/destination").permitAll()
					.antMatchers(HttpMethod.POST,"/destination").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PUT,"/destination/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PATCH,"/destination/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.DELETE,"/destination/*").hasAnyAuthority(
						"ADMIN"
					)
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/hotel").permitAll()
					.antMatchers(HttpMethod.POST,"/hotel").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PUT,"/hotel/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.PATCH,"/hotel/*").hasAnyAuthority(
						"MANAGER",
						"ADMIN"
					)
					.antMatchers(HttpMethod.DELETE,"/hotel/*").hasAnyAuthority(
						"ADMIN"
					)
			.and()
				.authorizeRequests()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/").permitAll();

		
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder delegatePasswordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}

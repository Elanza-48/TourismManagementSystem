package com.elanza48.TMS.security;

import com.elanza48.TMS.controller.filter.JwtRequestFilter;
import com.elanza48.TMS.model.dto.UserAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
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
				.authorizeRequests()
					.antMatchers("/user/*/*").hasAnyAuthority(
							UserAccount.UserRole.USER.toString(),
							UserAccount.UserRole.MANAGER.toString(),
							UserAccount.UserRole.ADMIN.toString())
					.antMatchers(HttpMethod.POST, "/user").permitAll()
					.antMatchers(HttpMethod.GET, "/user","/user/*").hasAuthority(
						UserAccount.UserRole.ADMIN.toString())
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/tourPackages").permitAll()
					.antMatchers(HttpMethod.POST,"/tourPackages").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PUT,"/tourPackages/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PATCH,"/tourPackages/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.DELETE,"/tourPackages/*").hasAnyAuthority(
						UserAccount.UserRole.ADMIN.toString()
					)
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/destination").permitAll()
					.antMatchers(HttpMethod.POST,"/destination").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PUT,"/destination/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PATCH,"/destination/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.DELETE,"/destination/*").hasAnyAuthority(
						UserAccount.UserRole.ADMIN.toString()
					)
			.and()
				.authorizeRequests()
					.antMatchers(HttpMethod.GET, "/hotel").permitAll()
					.antMatchers(HttpMethod.POST,"/hotel").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PUT,"/hotel/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.PATCH,"/hotel/*").hasAnyAuthority(
						UserAccount.UserRole.MANAGER.toString(),
						UserAccount.UserRole.ADMIN.toString()
					)
					.antMatchers(HttpMethod.DELETE,"/hotel/*").hasAnyAuthority(
						UserAccount.UserRole.ADMIN.toString()
					)
			.and()
				.authorizeRequests()
					.antMatchers("/authenticate").permitAll()
					.antMatchers("/").permitAll()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
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

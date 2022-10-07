package com.elanza48.TMS.security;

import com.elanza48.TMS.controller.filter.JwtRequestFilter;

import com.elanza48.TMS.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.lang.NonNull;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfigurer {

	private String mgmtEndPoint;
	private int mgmtPort;
	private int apiPort;
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void setActuatorEndPoint(@Value("${management.endpoints.web.base-path:/actuator}") String mgmtEndPoint) {
		this.mgmtEndPoint = mgmtEndPoint.concat("/**");
	}

	@Autowired
	public void setMgmtPort(@Value("${management.server.port}") int mgmtPort) {
		this.mgmtPort = mgmtPort;
	}

	@Autowired
	public void setApiPort(@Value("${server.port}") int apiPort) {
		this.apiPort = apiPort;
	}

	@Autowired
	public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	@Order(1)
	public SecurityFilterChain BasicSecurityFilter(HttpSecurity http) throws Exception {
		
		return http.requestMatcher(forPortAndPath(mgmtPort, mgmtEndPoint))
		.csrf()
		.disable()
		.authorizeHttpRequests()
		.anyRequest()
		.hasAnyRole(
				UserRole.ROLES.ADMIN.name(),
				UserRole.ROLES.MANAGER.name())
		.and().httpBasic()
		.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().build();
	}


	@Bean
	@Order(2)
	public SecurityFilterChain TokenSecurityFilter(HttpSecurity http) throws Exception {

		http.requestMatcher(forPortAndPath(apiPort,"/**"))
			.csrf()
			.disable()
			.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		return http.addFilterBefore(jwtRequestFilter, 
			UsernamePasswordAuthenticationFilter.class)
			.build();
	}


	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
			return (web) -> {
				web.ignoring().antMatchers(
					HttpMethod.GET,
					"/*.json");
			};
	}


	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	private RequestMatcher forPortAndPath(final int port, @NonNull	 final String pathPattern) {
    return new AndRequestMatcher(forPort(port), new AntPathRequestMatcher(pathPattern));
	}

	private RequestMatcher forPort(final int port){
		return (HttpServletRequest request) -> { return port == request.getLocalPort();};
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/status/**", config);
		return new CorsFilter(source);
	}

	@Bean
	public PasswordEncoder delegatePasswordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public RoleHierarchy roleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy(
				Arrays.stream(UserRole.ROLES.values())
						.map(UserRole.ROLES::getRole)
						.collect(Collectors.joining(" > ")));
		return roleHierarchy;
	}

	@Bean
	public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler() {
		DefaultWebSecurityExpressionHandler expressionHandler = new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy());
		return expressionHandler;
	}

}

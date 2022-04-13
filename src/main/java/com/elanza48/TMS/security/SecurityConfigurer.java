package com.elanza48.TMS.security;

import com.elanza48.TMS.controller.filter.JwtRequestFilter;

import com.elanza48.TMS.model.entity.UserRole;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.web.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
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
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.stream.Collectors;

@EnableWebSecurity
@EnableGlobalMethodSecurity(
	prePostEnabled = true,
	securedEnabled = true,
	jsr250Enabled = true
)
public class SecurityConfigurer {

	private String actuatorEndPoint;
	private UserDetailsService userDetailsService;
	private JwtRequestFilter jwtRequestFilter;

	@Autowired
	public void setActuatorEndPoint(@Value("${management.endpoints.web.base-path:/actuator}") String actuatorEndPoint) {
		this.actuatorEndPoint = actuatorEndPoint.concat("/**");
	}

	@Autowired
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Autowired
	public void setJwtRequestFilter(JwtRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Component
	@Order(1)
	public class BasicAuthConfigurer extends WebSecurityConfigurerAdapter{

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService);
        }

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.antMatcher(actuatorEndPoint)
					.csrf()
						.disable()
					.authorizeRequests()
						.anyRequest()
							.hasAnyRole(
								UserRole.ROLES.ADMIN.name(),
								UserRole.ROLES.MANAGER.name()
							)
					.and().httpBasic()
					.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		}
	}

	@Component
	@Order(2)
	public class JWTAuthConfigurer extends WebSecurityConfigurerAdapter{
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService);
		}

		@Override
		protected void configure(HttpSecurity http) throws Exception {

			http.antMatcher("/**").csrf().disable()
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(
					HttpMethod.GET,
					"/*.json"
			);
		}

		@Override
		@Bean
		public AuthenticationManager authenticationManagerBean() throws Exception {
			return super.authenticationManagerBean();
		}

	}

	@Bean
	public CorsFilter corsFilter(){
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
	public PasswordEncoder delegatePasswordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	@Bean
	public RoleHierarchy roleHierarchy(){
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		roleHierarchy.setHierarchy(
				Arrays.stream(UserRole.ROLES.values())
				.map(UserRole.ROLES::getRole)
				.collect(Collectors.joining(" > "))
		);
		return roleHierarchy;
	}

	@Bean
	public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler(){
		DefaultWebSecurityExpressionHandler expressionHandler= new DefaultWebSecurityExpressionHandler();
		expressionHandler.setRoleHierarchy(roleHierarchy());
		return expressionHandler;
	}

}

package com.jrp.pma.security;



import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	BCryptPasswordEncoder bCryptEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
			.usersByUsernameQuery("select username, password, enabled " + 
					"from user_accounts where username = ?")
			.authoritiesByUsernameQuery("select username, role " +
					"from user_accounts where username = ?")
			.dataSource(dataSource)
			.passwordEncoder(bCryptEncoder);
		
		
		
	}
	
	
	
	protected void configure(HttpSecurity http) throws Exception{
		
		http.authorizeRequests()
				.antMatchers("/").hasAnyRole("ADMIN","USER")
				.antMatchers("/projects/new").hasRole("ADMIN")
				.antMatchers("/projects/save").hasRole("ADMIN")
				.antMatchers("/projects/update").hasRole("ADMIN")
				.antMatchers("/projects/delete").hasRole("ADMIN")
				.antMatchers("/employees/new").hasRole("ADMIN")
				.antMatchers("/employees/save").hasRole("ADMIN")
				.antMatchers("/employees/save").hasRole("ADMIN")
				.antMatchers("/employees/update").hasRole("ADMIN")
				.antMatchers("/employees/delete").hasRole("ADMIN")
				.antMatchers("/employees").hasAnyRole("ADMIN","USER")
				.antMatchers("/projects").hasAnyRole("ADMIN","USER")
				.antMatchers("/register").permitAll()
				//.antMatchers("/resources/login/**").permitAll()
				//.anyRequest().authenticated()
				.and()
			.formLogin();
				//.loginPage("/login")
				//.permitAll();
		
		http.csrf().requireCsrfProtectionMatcher(new RequestMatcher() {
	        private Pattern allowedMethods = Pattern.compile("^(GET|DELETE|PATCH|PUT|POST)$");
	        private RegexRequestMatcher apiMatcher = new RegexRequestMatcher("/v[0-9]*/.*", null);

	        @Override
	        public boolean matches(HttpServletRequest request) {
	            // No CSRF due to allowedMethod
	            if(allowedMethods.matcher(request.getMethod()).matches())
	                return false;

	   
	            if(apiMatcher.matches(request))
	                return false;

	          
	            return true;
	        }
	    });
			
	
	}
	
	

}

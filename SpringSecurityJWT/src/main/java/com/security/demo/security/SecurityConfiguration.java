package com.security.demo.security;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.security.demo.filter.JWTRequestFilter;
import com.security.demo.model.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	

	@Autowired
	MyUserDetailsService myUserDetailService;
	
	@Autowired
	JWTRequestFilter jWTRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		    .authorizeHttpRequests().antMatchers("/authenticate").permitAll()
		    .anyRequest().authenticated()
		    .and().sessionManagement()
		    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jWTRequestFilter,UsernamePasswordAuthenticationFilter.class);
	}
	
	
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return  NoOpPasswordEncoder.getInstance();
	}
	

}

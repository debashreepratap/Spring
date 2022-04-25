package com.security.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.security.demo.model.MyUserDetailsService;
import com.security.demo.util.JWTUtil;
@Component
public class JWTRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	MyUserDetailsService myUserDetailService;
	
	@Autowired
	JWTUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
       final String authorizationHeader=request.getHeader("Authorization");
       String username=null;
       String jwt=null;
       
       if(authorizationHeader !=null &&authorizationHeader.startsWith("Bearer ")) {
    	   jwt= authorizationHeader.substring(7);
    	   username=jwtUtil.extractUsername(jwt);
       }
       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
    	   UserDetails userDetails = this.myUserDetailService.loadUserByUsername(username);
    	   if(jwtUtil.validtoken(jwt, userDetails)) {
    		   
    		   UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
    				    userDetails,null,userDetails.getAuthorities());
    		   usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    		   SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    		   
    	   }
       }
       filterChain.doFilter(request, response);
	}

}

package com.security.demo.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Service
public class JWTUtil {
	
	private String SECRET_KEY="secret";
	
	public String extractUsername(String token) {
		return extractClaim(token,Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token,Claims::getExpiration);
	}
	private <T>T extractClaim(String token,Function<Claims,T> claimResolver) {
		return null;
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJwt(token).getBody();
	}

	private Boolean  isTokenexpired(String token) {
		return extractExpiration(token).before(new Date());
		
	}
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims,userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() +1000*60*60*10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}
	
	public Boolean validtoken(String token,UserDetails userDetails) {
		final String username=extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenexpired(token));
		
	}
}

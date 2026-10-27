package com.smepro.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Service
public class qrcodeservice {

    private static final String SECRET_KEY = "your_secret_key";
    private static final long EXPIRATION_TIME = 86400000;
    
    
	 public static String generateToken(String username) {
	        return Jwts.builder()
	            .setSubject(username)
	            .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
	            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
	            .compact();
	    }
	
	 
	 public static String getUsernameFromToken(String token) {
	        Claims claims = Jwts.parser()
	            .setSigningKey(SECRET_KEY)
	            .parseClaimsJws(token)
	            .getBody();

	        return claims.getSubject();
	    }

	    public static boolean isTokenExpired(String token) {
	        Date expiration = Jwts.parser()
	            .setSigningKey(SECRET_KEY)
	            .parseClaimsJws(token)
	            .getBody()
	            .getExpiration();

	        return expiration.before(new Date());
	    }
	 
	    public String getClientIpAddress(HttpServletRequest request) {
	        String ipAddress = request.getHeader("X-Forwarded-For");

	        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	            ipAddress = request.getHeader("X-Real-IP");
	        }

	        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
	            ipAddress = request.getRemoteAddr();
	        }

	        // If you have multiple IP addresses in the X-Forwarded-For header, you may want to extract the first one.
	        if (ipAddress != null && ipAddress.contains(",")) {
	            ipAddress = ipAddress.split(",")[0].trim();
	        }

	        return ipAddress;
	    }
	    
	    
	    
	
}

package com.api.projectdashboard.services;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.api.projectdashboard.entities.ResponsibleEntity;
import com.api.projectdashboard.interfaces.TokenServiceInterface;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class TokenService implements TokenServiceInterface {
	    private static final String SECRET = "1";

	    public String generateToken(ResponsibleEntity entity){
	        try{
	            Algorithm algorithm = Algorithm.HMAC256(SECRET);
	            String token = JWT.create()
	                    .withIssuer("auth-api")
	                    .withSubject(entity.getEmail())
	                    .withExpiresAt(genExpirationDate())
	                    .sign(algorithm);
	            return token;
	        } catch (JWTCreationException exception) {
	            throw new RuntimeException("Error while generating token", exception);
	        }
	    }

	    public String validateToken(String token){
	        try {
	            Algorithm algorithm = Algorithm.HMAC256(SECRET);
	            return JWT.require(algorithm)
	                    .withIssuer("auth-api")
	                    .build()
	                    .verify(token)
	                    .getSubject();
	        } catch (JWTVerificationException exception){
	            return "";
	        }
	    }

	    private Instant genExpirationDate(){
	        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	    }
}

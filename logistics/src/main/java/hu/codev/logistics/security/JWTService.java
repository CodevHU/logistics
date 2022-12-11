package hu.codev.logistics.security;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;



@Service
public class JWTService {

	private static final String ISSUER = "LogisticsApp";
	private static final int EXPIRES_AT_MINUTES = 10;
	private static final String SECRET_KEY = "SecretKey";

	public String createToken(UserDetails principal) {

		String token = JWT.create().withSubject(principal.getUsername())
				.withArrayClaim("auth", principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).toArray(String[]::new))
				.withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(EXPIRES_AT_MINUTES)))
				.withIssuer(ISSUER).sign(Algorithm.HMAC256(SECRET_KEY));

		return token;
	}
	
	public UserDetails parseJwt(String jwtToken) {
		
		DecodedJWT decodedJwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
				.withIssuer(ISSUER)
				.build().verify(jwtToken);
		
		return new User(
				decodedJwt.getSubject(), 
				"", 
				decodedJwt.getClaim("auth").asList(String.class).stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
	}

	

	

}

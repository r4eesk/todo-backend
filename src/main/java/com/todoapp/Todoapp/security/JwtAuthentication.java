package com.todoapp.Todoapp.security;

import java.time.Instant;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
public class JwtAuthentication {

	@Autowired
	private JwtEncoder jwtEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

//	@PostMapping("/authenticate")
//	public JwtResponse authenticate(Authentication authentication) {
//		return new JwtResponse(createToken(authentication));
//	}
//
	private String createToken(Authentication authentication) {
		var claims = JwtClaimsSet.builder().issuer("self").issuedAt(Instant.now())
				.expiresAt(Instant.now().plusSeconds(60 * 30)).subject(authentication.getName())
				.claim("roles", createScope(authentication)).build();
		return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
	}

	@PostMapping("/authenticate")
	@CrossOrigin(origins = "https://todoapp.d3flz5w21lcbzk.amplifyapp.com", allowCredentials = "true")
	public ResponseEntity<JwtResponse> generateToken(@RequestBody JwtTokenRequest jwtTokenRequest,HttpServletResponse response) {

		var authenticationToken = new UsernamePasswordAuthenticationToken(jwtTokenRequest.username(),
				jwtTokenRequest.password());

		var authentication = authenticationManager.authenticate(authenticationToken);

		var token = createToken(authentication);
	
		Cookie userCookie = new Cookie("username", jwtTokenRequest.username());
		userCookie.setHttpOnly(true);
		//userCookie.setSecure(false);
		userCookie.setMaxAge(30*60);	
		userCookie.setPath("/");
		//userCookie.setDomain("localhost:3000");
		response.addCookie(userCookie);
		Cookie jwtCookie = new Cookie("jwt", token);
		jwtCookie.setHttpOnly(true);
		//jwtCookie.setSecure(false);
		jwtCookie.setMaxAge(30*60);	
		jwtCookie.setPath("/");
		//jwtCookie.setDomain("localhost:3000");
		response.addCookie(jwtCookie);
		System.out.println(jwtTokenRequest.username());
		System.out.println(jwtTokenRequest.password());
		
		return ResponseEntity.ok(new JwtResponse(token));
	}

	private String createScope(Authentication authentication) {
		return authentication.getAuthorities().stream().map(a -> a.getAuthority()).collect(Collectors.joining(" "));
	}
	
}



record JwtResponse(String token) {
}

record JwtTokenRequest(String username, String password) {
}

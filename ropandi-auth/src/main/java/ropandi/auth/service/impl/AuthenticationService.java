package ropandi.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import java.util.Objects;
import ropandi.auth.model.CredentialData;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
@Component
public class AuthenticationService implements AuthenticationProvider {
	@Autowired
	private CredentialsDetailsService credentialUserDetailsService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String nickname = authentication.getName();
	    String password = (String) authentication.getCredentials();
	    CredentialData credentialData = (CredentialData) credentialUserDetailsService.loadUserByUsername(nickname);
	    if (Objects.isNull(credentialData) || !credentialData.getId().equalsIgnoreCase(nickname)) {
	      throw new BadCredentialsException("email not found or invalid.");
	    }
	    if (!password.equals(credentialData.getPassword())) {
	      throw new BadCredentialsException("wrong password.");
	    }
	    return new UsernamePasswordAuthenticationToken(credentialData, password, credentialData.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		 return true;
	}

}

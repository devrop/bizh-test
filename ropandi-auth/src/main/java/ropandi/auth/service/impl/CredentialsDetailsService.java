package ropandi.auth.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import ropandi.auth.entity.Credential;
import ropandi.auth.model.CredentialData;
import ropandi.auth.model.ScopeData;
import ropandi.auth.repo.CredentialRepository;
import ropandi.auth.repo.ScopeRepository;
@Component
public class CredentialsDetailsService implements UserDetailsService {

	//@Autowired
	//private CredentialRepository credentialRepository;
	//@Autowired
	//private ScopeRepository scopeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		final Credential credential = null;// this.credentialRepository.findOne(id);
		final Set<ScopeData> scopeData = null; /*this.scopeRepository
				.findByClientId(id)
				.stream().map(d-> {
					return ScopeData.builder().id(d.getScopePK().getScopeId()).value(d.getValue()).build();
					}).collect(Collectors.toSet());
				
		*/
		return CredentialData.builder()
				.id(credential.getId())
				.email(credential.getEmail()).password(credential.getPassword()).scopes(scopeData).build();
	}

}

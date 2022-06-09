package ropandi.auth.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ropandi.auth.entity.Credential;
import ropandi.auth.entity.Scope;
import ropandi.auth.entity.ScopePK;
import ropandi.auth.model.CredentialRequest;
import ropandi.auth.repo.CredentialRepository;
import ropandi.auth.repo.ScopeRepository;
import ropandi.auth.service.ICredentialService;

@Service
public class CredentialService implements ICredentialService {

	//@Autowired
	//private CredentialRepository credentialRepository;

	//@Autowired
	//private ScopeRepository scopeRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public CredentialRequest newCredential(CredentialRequest credentialRequest) {
		// TODO Auto-generated method stub

		//scopeRepository.save(
		//		Scope.builder().scopePK(ScopePK.builder().clientId(credentialRequest.getId()).scopeId("1").build())
				//		.value("operator").build());

		final Credential credential = Credential.builder().id(credentialRequest.getId())
				.email(credentialRequest.getEmail()).name(credentialRequest.getName())
				.password(this.passwordEncoder.encode(credentialRequest.getPassword())).build();
		//this.credentialRepository.save(credential);

		return credentialRequest;
	}

}

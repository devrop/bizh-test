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
import ropandi.auth.repository.CredentialRepository;
import ropandi.auth.repository.ScopeRepository;
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
;
        /*
		final Credential credential = Credential.builder().id(credentialRequest.getId())
				.email(credentialRequest.getEmail()).name(credentialRequest.getName())
				.password(this.passwordEncoder.encode(credentialRequest.getPassword())).build();
		this.credentialRepository.save(credential);
        this.scopeRepository.save(Scope.builder().scopePK(ScopePK.builder().clientId(credentialRequest.getId()).scopeId("operator").build()).value("Opeartor").build());
		return credentialRequest;
		*/
       return null;
	}

}

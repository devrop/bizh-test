package ropandi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ropandi.auth.model.CredentialRequest;
import ropandi.auth.service.ICredentialService;

@RestController
@RequestMapping("/register")
public class RegisterController {

	@Autowired
	private ICredentialService credentialService;
	 @PostMapping
	  public ResponseEntity<CredentialRequest> newCredential(@RequestBody CredentialRequest credentialRequest){
	    return ResponseEntity.ok(this.credentialService.newCredential(credentialRequest));
	  }
}

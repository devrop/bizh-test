package ropandi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ropandi.auth.model.CredentialRequest;
import ropandi.auth.service.ICredentialService;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	
	
	@Autowired
	private ICredentialService credentialService;
	 @PostMapping
	  public ResponseEntity<CredentialRequest> newCredential(@RequestBody CredentialRequest credentialRequest){
	    return ResponseEntity.ok(this.credentialService.newCredential(credentialRequest));
	  }
	 @GetMapping("mem")
	 private String getAllData(@PathVariable("id") String id){
		 System.out.println("CALL ");
		 return inMemoryUserDetailsManager.loadUserByUsername(id).getUsername();
	 }
}


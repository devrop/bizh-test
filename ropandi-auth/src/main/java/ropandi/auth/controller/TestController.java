package ropandi.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("get")
public class TestController {

	@Autowired
	private InMemoryUserDetailsManager inMemoryUserDetailsManager;
	 @GetMapping("id/{id}")
	 private String getAllData(@PathVariable("id") String id){
		 System.out.println("CALLDDDD " );
		 System.out.println("CALL " + inMemoryUserDetailsManager.loadUserByUsername(id).getUsername());
		 return inMemoryUserDetailsManager.loadUserByUsername(id).getUsername();
	 }
}

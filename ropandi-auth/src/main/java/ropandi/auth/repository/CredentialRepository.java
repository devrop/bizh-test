package ropandi.auth.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ropandi.auth.entity.Credential;
@Configuration
@Repository
public interface CredentialRepository extends JpaRepository<Credential,String> {

	
	
	
}

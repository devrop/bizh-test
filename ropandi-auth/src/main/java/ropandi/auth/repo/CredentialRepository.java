package ropandi.auth.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ropandi.auth.entity.Credential;
@Repository
public interface CredentialRepository extends JpaRepository<Credential,String> {

	
	
	
}

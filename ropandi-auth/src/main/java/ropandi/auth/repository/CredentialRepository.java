package ropandi.auth.repository;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;

import ropandi.auth.entity.Credential;
@Configuration
@Repository
public interface CredentialRepository extends JpaRepository<Credential,String> {

	@Query("select p from Credential p where p.email =:email")
	public Optional<Credential> findByEmail(@Param("email") String email);
	
	
}

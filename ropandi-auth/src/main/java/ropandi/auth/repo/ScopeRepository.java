package ropandi.auth.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ropandi.auth.entity.Scope;
import ropandi.auth.entity.ScopePK;
@Repository
public interface ScopeRepository  extends JpaRepository<Scope,ScopePK>{

	@Query("select s from Scope s where s.scopePK.clientId =:clientId")
	List<Scope> findByClientId(@Param("clientId") String clientId);
}

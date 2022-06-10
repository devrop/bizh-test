package ropandi.rack.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ropandi.rack.entity.MstRack;

@Repository
public interface RackRepository extends JpaRepository<MstRack, Long> {

	
	@Query("select p from MstRack p where p.rackCode in :rackCodes ")
	public List<MstRack> findAllByRackCode(@Param("rackCodes") Set<Long> rackCodes);
}

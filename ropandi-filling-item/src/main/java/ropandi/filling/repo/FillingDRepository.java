package ropandi.filling.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ropandi.filling.entity.TrxFillingItemDtl;

@Repository
public interface FillingDRepository extends JpaRepository<TrxFillingItemDtl, String> {

	
	@Query("select t from TrxFillingItemDtl t where t.trxNo =:trxNo")
	public List<TrxFillingItemDtl> findByTrxNo(@Param("trxNo") String trxNo);
}

package ropandi.filling.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ropandi.filling.entity.TrxFillingItemHdr;
@Repository
public interface FillingHRepository extends JpaRepository<TrxFillingItemHdr, String>{

}

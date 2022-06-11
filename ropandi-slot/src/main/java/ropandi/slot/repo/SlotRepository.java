package ropandi.slot.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ropandi.slot.entity.MstSlot;
@Repository
public interface SlotRepository extends JpaRepository<MstSlot, Long>{

	@Modifying
	@Query("update MstSlot s set s.usedQty=:usedCapacity where s.slotCode=:slotCode")
	public int updateSlotUsedQty(@Param("usedCapacity") Long usedCapacity, @Param("slotCode") Long slotCode);
}

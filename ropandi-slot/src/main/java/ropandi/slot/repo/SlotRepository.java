package ropandi.slot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ropandi.slot.entity.MstSlot;
@Repository
public interface SlotRepository extends JpaRepository<MstSlot, Long>{

}

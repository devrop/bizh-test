package ropandi.box.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ropandi.box.entity.MstBox;

@Repository
public interface BoxRepository extends JpaRepository<MstBox, Long> {

	
}

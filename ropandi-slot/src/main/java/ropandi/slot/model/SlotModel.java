package ropandi.slot.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ropandi.slot.entity.MstSlot;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class SlotModel implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long slotCode;
	private String slotName;
	private Long boxCode;
	private Long rackCode;
	private Long slotCapacity;
	private Long usedQty;
}

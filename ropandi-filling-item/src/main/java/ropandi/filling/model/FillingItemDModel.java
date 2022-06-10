package ropandi.filling.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ropandi.filling.entity.TrxFillingItemDtl;
import ropandi.filling.entity.TrxFillingItemDtl.TrxFillingItemDtlBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class FillingItemDModel {

	private String trxNo;
	private String slotCode;
	private String slotName;
	private String boxCode;
	private String boxName;
	private String rackCode;
	private String rackName;
	private String itemName;
}

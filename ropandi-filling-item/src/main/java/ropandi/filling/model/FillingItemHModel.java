package ropandi.filling.model;

import java.util.List;

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
public class FillingItemHModel {
	private String trxNo;
	private String trxName;
	private Long qty;
	private List<FillingItemDModel> details;
	
}

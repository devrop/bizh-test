package ropandi.filling.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ropandi.filling.entity.TrxFillingItemDtl.TrxFillingItemDtlBuilder;

@Entity
@Table(name = "trxfillingitemhdr")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TrxFillingItemHdr {
    @Id
	private String trxNo;
	private String trxName;
	private String fillType;
	private Long slotCode;
	private Long qty;
	
}

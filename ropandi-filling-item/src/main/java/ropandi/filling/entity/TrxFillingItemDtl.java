package ropandi.filling.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trxfillingitemdtl")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class TrxFillingItemDtl {
	@Id
	private String trxNo;
	private String itemName;
	
	
}

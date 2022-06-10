package ropandi.box.share.model;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class RackModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long rackCode;
	private String rackName;
	
	private Set<Long> rackCodes;
	
	
}

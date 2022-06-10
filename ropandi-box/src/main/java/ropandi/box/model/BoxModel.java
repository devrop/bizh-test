package ropandi.box.model;

import java.io.Serializable;

import org.springframework.stereotype.Service;

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
public class BoxModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long boxCode;
	private Long rackCode;
	private String rackName;
	private String boxName;
}

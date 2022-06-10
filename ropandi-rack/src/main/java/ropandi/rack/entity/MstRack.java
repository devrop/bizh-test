package ropandi.rack.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "mstrack")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class MstRack implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="rack_code")
	private Long rackCode;
	@Column(name="rack_name")
	private String rackName;
	
	
	

}

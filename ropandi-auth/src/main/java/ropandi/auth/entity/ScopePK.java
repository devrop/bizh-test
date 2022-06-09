package ropandi.auth.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class ScopePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Basic(optional = false)
    private String clientId;
    @Basic(optional = false)
    private String scopeId;
}

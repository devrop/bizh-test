package ropandi.auth.model;

import java.io.Serializable;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ropandi.auth.entity.Scope;
import ropandi.auth.model.CredentialData.CredentialDataBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScopeData implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String value;
}

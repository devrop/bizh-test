package ropandi.rack.base;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@NoArgsConstructor
@Setter
@Getter
@AllArgsConstructor
public class BaseResponse<R> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private long totalRecords;
	private R contents;
	
	
	
}

package ropandi.box.share.model;

import java.io.Serializable;

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
public class RestResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private long totalRecords;
	private Object contents;
}

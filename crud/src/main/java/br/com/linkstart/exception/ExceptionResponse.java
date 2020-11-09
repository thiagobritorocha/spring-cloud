package br.com.linkstart.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class ExceptionResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7126635871741007565L;
	
	private Date timestamp;
	private String message;
	private String details;

}

package com.uwca.operation.common.utils;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class BaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int returncode = 0;

	private String message = "";
	
	public int getReturncode() {
		return returncode;
	}

	public void setReturncode(int returncode) {
		this.returncode = returncode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}

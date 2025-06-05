package com.test.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class ResponseStatus<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	int code;
	String status;
	String message; 
	T data;
	Date timestamp;
}

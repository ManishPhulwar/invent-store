package com.pcm.invent.store.error;

import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
public class BadRequest {

	public static final String DEFAULT_MESSAGE = "Bad Request.";

	private final String message;

	public BadRequest(String message) {
		super();
		this.message = message;
	}

	public ResponseEntity<BadRequest> asResponseEntity() {
		return ResponseEntity.badRequest().body(this);
	}

	public String getMessage() {
		return message;
	}

}

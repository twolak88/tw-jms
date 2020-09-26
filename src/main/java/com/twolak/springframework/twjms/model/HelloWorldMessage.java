package com.twolak.springframework.twjms.model;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author twolak
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelloWorldMessage implements Serializable{
	
	private static final long serialVersionUID = 8759781869526356186L;
	
	private UUID id;
	private String message;
}
 
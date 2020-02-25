package br.com.cabal.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Status {
	REGISTER_ACTIVE(1),
	REGISTER_INACTIVE(0);
	
	private int status;
}

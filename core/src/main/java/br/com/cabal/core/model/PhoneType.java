package br.com.cabal.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PhoneType {
	HOME_PHONE(1),
	MOBILE_PHONE(2),
	WORK_PHONE(3);
	
	private int type;
}

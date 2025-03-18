package com.bjb.pockit.constant;

public enum TransactionType {

	SUCCESS("0000"),
	GENERAL_ERROR("0005")
	;

	private String code;

	private TransactionType(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}

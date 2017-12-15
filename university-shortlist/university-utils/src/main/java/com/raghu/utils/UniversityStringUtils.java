package com.raghu.utils;

public enum UniversityStringUtils {
	
	UNIVERSITY_NAME("Name"),
	UNIVERSITY_CITY("City"),
	UNIVERSITY_COUNTRY("Country")
	
	;
	
	private final String string;
	
	private UniversityStringUtils(String string) {
		this.string = string;
	}
	
	public String getString() {
		return this.string;
	}
}

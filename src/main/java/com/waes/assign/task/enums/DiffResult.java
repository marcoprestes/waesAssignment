package com.waes.assign.task.enums;

public enum DiffResult {

	NOT_VALID_INPUT("The input provided is not valid.", "Please check the input."),
	EQUAL("Equals", "Left and right value contains the same value."), 
	NOT_EQUAL_DIFF_LENGHT("Not equals. Different length.", "Left and right have different length."), 
	NOT_EQUAL("Not equals.","Differences are displayed on the result.");
	
	private String caption;
	
	private String description;
	
	private DiffResult(String caption, String description) {
		this.caption = caption;
		this.description = description;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public String getDescription() {
		return description;
	}
	
}

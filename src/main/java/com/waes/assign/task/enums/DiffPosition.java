package com.waes.assign.task.enums;

public enum DiffPosition {

	LEFT("left"), RIGHT("right");
	
	String caption;
	
	private DiffPosition(String caption) {
		this.caption = caption;
	}
	
	public String getCaption() {
		return this.caption;
	}
	
}

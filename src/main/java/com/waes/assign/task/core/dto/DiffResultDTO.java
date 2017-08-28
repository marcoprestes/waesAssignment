package com.waes.assign.task.core.dto;

import java.util.List;
import java.util.Map;

import com.waes.assign.task.enums.DiffResult;

public class DiffResultDTO {

	/**
	 * Flag to identify if there is any differences between the two inputs.
	 */
	private boolean hasDifferences = false;
	
	/**
	 * Result description.
	 */
	private DiffResult result;
	
	/**
	 * If some of the requirements to perform the diff was not satisfied.
	 */
	private String msg;
	
	/**
	 * Differences between left and right inputs.
	 */
	private String diffs;
	
	private Map<Integer, List<String>> changedLines;

	public DiffResultDTO() {
		
	}
	
	public DiffResultDTO(DiffResult result, String msg) {
		this.result = result;
		this.msg = msg;
	}
	
	public boolean isHasDifferences() {
		return hasDifferences;
	}

	public void setHasDifferences(boolean hasDifferences) {
		this.hasDifferences = hasDifferences;
	}

	public DiffResult getResult() {
		return result;
	}

	public void setResult(DiffResult result) {
		this.result = result;
	}
	
	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getDiffs() {
		return diffs;
	}

	public void setDiffs(String diffs) {
		this.diffs = diffs;
	}

	public Map<Integer, List<String>> getChangedLines() {
		return changedLines;
	}

	public void setChangedLines(Map<Integer, List<String>> changedLines) {
		this.changedLines = changedLines;
	}
	
}

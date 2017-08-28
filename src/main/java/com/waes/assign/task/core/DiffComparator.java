package com.waes.assign.task.core;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.waes.assign.task.core.dto.DiffResultDTO;
import com.waes.assign.task.enums.DiffResult;

@Service
public class DiffComparator {

	public DiffResultDTO compare(String leftValue, String rightValue) {
		
		if (leftValue.equals(rightValue)) {
			return new DiffResultDTO(DiffResult.EQUAL, "Left and right values are the equals.");
		}
		
		if (leftValue.length() != rightValue.length()) {
			return new DiffResultDTO(DiffResult.NOT_EQUAL_DIFF_LENGHT, "Left and right have different lenght.");
		}
		
		String convertedLeftValue = convertBase64ToString(leftValue);
		String convertedRightValue = convertBase64ToString(rightValue);

		List<String> leftValueLines = convertValueIntoStringArray(convertedLeftValue);
		List<String> rightValueLines = convertValueIntoStringArray(convertedRightValue);
		
		Map<Integer, List<String>> changedLines = findDifferences(leftValueLines, rightValueLines);
		
		return createResult(changedLines);
		
	}
	
	/**
	 * Converts string provided on based64 to a strin value.
	 * @param value Value to be converted.
	 * @return Converted base64 value.
	 */
	private String convertBase64ToString(String value) {
		
		String convertedValue = null;
		
		try {
			byte[] base64ConvertedValue = Base64.getDecoder().decode(value);
			convertedValue = new String(base64ConvertedValue);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		return convertedValue;		
		
	}
	
	/**
	 * Convert the specified value into an array of string lines.
	 * @param value Value to be splitted into an array
	 * @return String array containing the lines
	 */
	private List<String> convertValueIntoStringArray(String value) {
		List<String> lines = new ArrayList<>();
		Scanner sc = new Scanner(value);
		while(sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}
		sc.close();
		return lines;
	}
	
	/**
	 * Aggregate all differences based on the line number.
	 * @param leftValueLines
	 * @param rightValueLines
	 * @return Map where the key is the line number and the value are the lines from each side
	 */
	private Map<Integer, List<String>> findDifferences(List<String> leftValueLines, List<String> rightValueLines) {
		Map<Integer, List<String>> changedLines = new HashMap<>();
		
		for (int lineNo = 0 ; lineNo < leftValueLines.size() ; lineNo++) {
			
			if (lineNo > rightValueLines.size()) {
				break;
			}
			
			if (!leftValueLines.get(lineNo).equals(rightValueLines.get(lineNo))) {
				List<String> lines = new ArrayList<>();
				lines.add(leftValueLines.get(lineNo));
				lines.add(rightValueLines.get(lineNo));
				changedLines.put(lineNo, lines);
			}
			
		}
		
		return changedLines;
	}
	
	/**
	 * Describes in which line there are changes and what are the changes.
	 * @param changedLines
	 * @return Formatted text containing the changes.
	 */
	private String writeChanges(Map<Integer, List<String>> changedLines) {
		StringBuilder builder = new StringBuilder("The following changes were found:\n\n");
		for (Entry<Integer, List<String>> entry : changedLines.entrySet()) {
			builder.append("Line No.: " + entry.getKey() + "\n");
			builder.append("Left Line (lenght " + entry.getValue().get(0).length() + "): " + entry.getValue().get(0) + "\n");
			builder.append("Right Line (lenght " + entry.getValue().get(1).length() + "): " + entry.getValue().get(1) + "\n");
			builder.append("---------------------------------------------------------------------------------------------\n");
		}
		return builder.toString();
	}
	
	/**
	 * Create the result structure that will be converted into JSON object on the rest API.
	 * @param changedLines
	 * @return DTO properly filled with information related to difference
	 */
	private DiffResultDTO createResult(Map<Integer, List<String>> changedLines) {
		DiffResultDTO dto = new DiffResultDTO();
		dto.setResult(DiffResult.NOT_EQUAL);
		dto.setHasDifferences(true);
		dto.setChangedLines(changedLines);
		dto.setDiffs(writeChanges(changedLines));
		return dto;
	}
	
}

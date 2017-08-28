package com.waes.assign.task.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waes.assign.task.core.DiffComparator;
import com.waes.assign.task.core.dto.DiffResultDTO;
import com.waes.assign.task.enums.DiffPosition;
import com.waes.assign.task.enums.DiffResult;

@Service
public class DiffService {

	private static final String INVALID_ID_VALUE = "ID value provided is not valid. Only digits are allowed.";
	
	private static final String ID_NOT_FOUND = "ID {id} not found.";

	private static final String EMPTY_VALUE = "{position} value not set for ID {id}.";

	@Autowired
	private DiffComparator comparator;

	/**
	 * Structure responsible to handle all difference structures based on a Long ID.
	 */
	private Map<Long, Map<DiffPosition, String>> diffValues;

	/**
	 * Initializes values to be compared structure.
	 */
	@PostConstruct
	public void init() {
		diffValues = new HashMap<>();
	}

	/**
	 * Set the identification (if it does not exists) and set the value in the specified position.
	 * @param id Identification of the difference compare
	 * @param value Base64 value
	 * @param position Position in which the value is specified
	 */
	public String addValue(String strId, String value, DiffPosition position) {
		
		if (!isValidID(strId)) {
			return INVALID_ID_VALUE;
		}
		
		Long id = Long.valueOf(strId);
		
		String msg = "Diff Identification {id}: {position} {action}: {value}.";
		String action = "has been set with value";
		String oldValue = null;
		String oldValueMsg = "";

		if (diffValues.containsKey(id)) {
			if (diffValues.get(id).containsKey(position)) {
				if (diffValues.get(id).get(position).equals(value)) {
					action = "already have the value provided.";
				} else {
					action = "has been updated with value";
					oldValue = diffValues.get(id).get(position);
					oldValueMsg = "(Old value: {oldValue})".replace("{oldValue}", oldValue);
				}
			}
		} else {
			diffValues.put(id, new HashMap<>());
		}

		diffValues.get(id).put(position, value);

		msg = msg.replace("{id}", id.toString());
		msg = msg.replace("{position}", position.getCaption());
		msg = msg.replace("{action}", action);
		msg = msg.replace("{value}", value);

		return msg + oldValueMsg;
	}

	/**
	 * Validates id input and calls the comparator to find the differences between files.
	 * @param id Identification of the difference compare
	 * @return {@inheritDoc}
	 */
	public DiffResultDTO compareValuesForId(String strId) {

		if (!isValidID(strId)) {
			return new DiffResultDTO(DiffResult.NOT_VALID_INPUT, INVALID_ID_VALUE);
		}
		
		Long id = Long.valueOf(strId);
		
		if (!diffValues.containsKey(id)) {
			return new DiffResultDTO(DiffResult.NOT_VALID_INPUT, ID_NOT_FOUND.replace("{id}", strId));
		}
		
		String leftValue = diffValues.get(id).get(DiffPosition.LEFT);
		String rightValue = diffValues.get(id).get(DiffPosition.RIGHT);

		if (leftValue == null) {
			return new DiffResultDTO(DiffResult.NOT_VALID_INPUT, EMPTY_VALUE.replace("{position}", DiffPosition.LEFT.getCaption()));
		}

		if (rightValue == null) {
			return new DiffResultDTO(DiffResult.NOT_VALID_INPUT, EMPTY_VALUE.replace("{position}", DiffPosition.RIGHT.getCaption()));
		}

		return comparator.compare(leftValue, rightValue);

	}
	
	/**
	 * Retrieves the base64 value for the specified id and position
	 * @param strId Identification for the diff process
	 * @param position Position of the value to be retrieved
	 * @return Base64 value
	 */
	public String getValue(String strId, DiffPosition position) {
		
		if (!isValidID(strId)) {
			return INVALID_ID_VALUE;
		}
		
		Long id = Long.valueOf(strId);
		
		if (!diffValues.containsKey(id)) {
			return ID_NOT_FOUND.replace("{id}", strId);
		}
		
		return diffValues.get(id).get(position);
	}
	
	/**
	 * Validates if the value provided for ID is properly set.
	 * @param id Identification of the difference compare
	 * @return Boolean value that defines if the id is a valid number.
	 */
	private boolean isValidID(String id) {
		return id != null && NumberUtils.isNumber(id.toString());
	}

	
}

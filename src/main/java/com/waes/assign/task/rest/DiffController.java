package com.waes.assign.task.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.waes.assign.task.core.dto.DiffResultDTO;
import com.waes.assign.task.enums.DiffPosition;
import com.waes.assign.task.service.DiffService;

@RestController
public class DiffController {
	
	/**
	 * Service responsible to handle on actions related to compare differences between base64 strings.
	 */
	@Autowired
	private DiffService service;

	/**
	 * Set the left value to be compared with a specific identification.
	 * @param id A long value to identify the difference after the input of both left and right value
	 * @param value Value to be set as right value
	 */
	@PostMapping(path = "/v1/diff/{id}/left")
	public String addDiffLeftValue(@PathVariable(value = "id", required = true) String id,
			@RequestParam(value = "value", required = true) String value) {
		return service.addValue(id, value, DiffPosition.LEFT);
	}
	
	/**
	 * Get the left value to be compared for a specific identification.
	 * @param id Identification to retrieve the value
	 */
	@GetMapping(path = "/v1/diff/{id}/get/left")
	public String getLeftValue(@PathVariable(value = "id", required = true) String id) {
		return service.getValue(id, DiffPosition.LEFT);
	}

	/**
	 * Set the right value to be compared with a specific identification.
	 * @param id A long value to identify the difference after the input of both left and right value
	 * @param value Value to be set as right value
	 */
	@PostMapping(path = "/v1/diff/{id}/right")
	public String addDiffRightValue(@PathVariable(value = "id", required = true) String id,
			@RequestParam(value = "value", required = true) String value) {
		return service.addValue(id, value, DiffPosition.RIGHT);
	}
	
	/**
	 * Get the right value to be compared for a specific identification.
	 * @param id Identification to retrieve the value
	 */
	@GetMapping(path = "/v1/diff/{id}/get/right")
	public String getRightValue(@PathVariable(value = "id", required = true) String id) {
		return service.getValue(id, DiffPosition.RIGHT);
	}

	/**
	 * Responsible to return the value with the difference between the left and right value.
	 * The is going be return as a base 64 encoded value.
	 * @param id A long value to identify the difference after the input of both left and right value
	 * @return DiffResultDTO converted into JSON value
	 */
	@GetMapping(path = "/v1/diff/{id}")
	public DiffResultDTO diffResult(@PathVariable(value = "id", required = true) String id) {
		return service.compareValuesForId(id);
	}
	
}

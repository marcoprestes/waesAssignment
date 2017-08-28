package com.waes.assign.task.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.waes.assign.task.enums.DiffPosition;

public class DiffServiceTest {

	@Mock
	DiffService service;
	
	@Rule 
	public MockitoRule mockitoRule = MockitoJUnit.rule(); 
	
	final String file1 = "RnJ1aXRzOg0KDQpiYW5hbmENCmFwcGxlDQpwaW5lYXBwbGUNCm9yYW5nZQ0KZ3JhcGVmcnVpdA==";
	
	final String file2 = "";
	
	final String file3 = "";
	
	@Test
	public void setLeftValueTest() {
		String strId = "15";
		String value = "RnJ1aXRzOg0KDQpiYW5hbmENCmFwcGxlDQpwaW5lYXBwbGUNCm9yYW5nZQ0KZ3JhcGVmcnVpdA==";
		DiffPosition position = DiffPosition.LEFT;
		service.addValue(strId, value, position);
		verify(service).addValue(strId, value, position);
		String retrievedValue = service.getValue(strId, position);
		assertEquals(value, retrievedValue);
	}
	
	@Test
	public void setRightValueTest() {
		
	}
	
}

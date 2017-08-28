package com.waes.assign.task.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.waes.assign.task.service.DiffService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DiffController.class, secure = false)
public class DiffControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private DiffService diffService;


	@Test
	public void retrieveDetailsForCourse() throws Exception {

	}

}

package com.vaganov.task;

import com.vaganov.task.task.PostController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class SecurityApplicationTests {

	@Autowired
	private PostController controller;


	@Test
	void contextLoads() {
		Assertions.assertNotNull(controller);
	}

}

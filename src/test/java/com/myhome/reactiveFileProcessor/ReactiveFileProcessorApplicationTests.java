package com.myhome.reactiveFileProcessor;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.IOException;

@SpringBootTest
class ReactiveFileProcessorApplicationTests {

	@Value("classpath:com/myhome/reactiveFileProcessor/data.csv")
	Resource dataFile;

	@Test
	void contextLoads() {
	}

	@Test
	void fileResourceLoadTest() throws IOException {
		Assertions.assertNotNull(dataFile);
		Assertions.assertTrue(dataFile.getFile().canRead());
	}

	@Test
	void fileParsingTest() {

	}
}

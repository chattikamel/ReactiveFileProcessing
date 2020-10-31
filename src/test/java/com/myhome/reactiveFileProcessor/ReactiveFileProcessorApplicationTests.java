package com.myhome.reactiveFileProcessor;

import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ReactiveFileProcessorApplicationTests {

	@Value("classpath:com/myhome/reactiveFileProcessor/data.csv")
	Resource dataFile;

	@Test
	void contextLoads() {
	}

	@Test
	void fileResourceLoadTest() throws IOException {
		assertNotNull(dataFile);
		assertTrue(dataFile.getFile().canRead());
	}

	@Test
	void fileParsingTest() throws IOException {

		FileParser fileParser = new FileParser(dataFile.getFile());
		List<Person> persons = new CsvToBeanBuilder(new FileReader(dataFile.getFile()))
				.withType(Person.class).build().parse();

		assertEquals(3, persons.size());

		Person expectedFirstPerson = Person.builder()
				.firstName("kamel")
				.lastName("happy")
				.age(15)
				.build();


		assertEquals(expectedFirstPerson, persons.get(0));

		Person expectedSecondPerson = Person.builder()
				.firstName("adam")
				.lastName("unhappy")
				.age(20)
				.build();

		assertEquals(expectedSecondPerson, persons.get(1));


	}
}

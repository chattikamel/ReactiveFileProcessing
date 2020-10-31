package com.myhome.reactiveFileProcessor;

import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.tuple.ImmutablePair.of;
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

        List<Person> persons = new CsvToBeanBuilder(new FileReader(dataFile.getFile()))
                .withType(Person.class).build().parse();

        assertEquals(3, persons.size());

        asList(of(Person.builder()
                        .firstName("kamel")
                        .lastName("happy")
                        .age(15)
                        .build(), 0),

                of(Person.builder()
                        .firstName("mario")
                        .lastName("game")
                        .age(25)
                        .build(), 2),

                of(Person.builder()
                        .firstName("adam")
                        .lastName("unhappy")
                        .age(20)
                        .build(), 1)

        )
                .forEach(p -> assertEquals(p.getLeft(), persons.get(p.getRight())));


    }


}

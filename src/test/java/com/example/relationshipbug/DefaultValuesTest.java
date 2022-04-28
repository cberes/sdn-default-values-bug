package com.example.relationshipbug;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.neo4j.core.Neo4jClient;

import java.util.UUID;

@SpringBootTest
class DefaultValuesTest {
    private final MyNodeRepository myNodeRepository;
    private final Neo4jClient client;

    @Autowired
    public DefaultValuesTest(MyNodeRepository myNodeRepository, Neo4jClient client) {
        this.myNodeRepository = myNodeRepository;
        this.client = client;
    }

    @BeforeEach
    void setup() {
        myNodeRepository.deleteAll();
    }

    @Test
    void queryForNodeWithDefaultValues() {
        String id = UUID.randomUUID().toString();
        String name = "Nikola Tesla";
        client.query("CREATE (:MyNode {id: $id, name: $name})")
                .bind(id).to("id")
                .bind(name).to("name")
                .run();

        MyNode found = myNodeRepository.findById(id).get();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(found.getName()).as("name").isEqualTo(name);
        softly.assertThat(found.isActive()).as("active").isTrue();
        softly.assertThat(found.getAge()).as("age").isEqualTo(25);
        softly.assertThat(found.getHobbies()).as("hobbies").containsExactly("cooking");
        softly.assertAll();
    }
}

package com.example.relationshipbug;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.List;

import static java.util.Collections.singletonList;

@Getter
@Setter
@Node
public class MyNode {

    @Id
    @GeneratedValue(generatorClass = SomeStringGenerator.class)
    private String id;

    @Property("name")
    private String name;

    @Property("active")
    private boolean active = true;

    @Property("age")
    private Integer age = 25;

    @Property("hobbies")
    private List<String> hobbies = singletonList("cooking");
}

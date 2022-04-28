# sdn-default-values-bug

[SDN](https://github.com/spring-projects/spring-data-neo4j) does not respect default values set in entities.

For example, say you have an entity like this

    @Getter
    @Setter
    @Node
    public abstract class MyNode {
      @Property("name")
      private String name;

      @Property("active")
      private boolean active = true;

      @Property("age")
      private Integer age = 25;
    }

And you create a new node without the `active` and `age` properties

    CREATE (:MyNode {id: '123', name: 'Nikola Tesla'})

When you query for the node, `active` will be `false` and `age` will be `null`

    MyNode found = myNodeRepository.findById("123").get();
    if (!found.isActive()) {                // active is false but it should be true
      found.setAge(found.getAge() - 1);     // age is null: NullPointerException
    }

This example has never worked correctly in SDN as far as I know. Specifically I tried this with 6.3.1 and 6.2.0.

However, this differs from OGM, which uses the default values set in an entity.
The tests in this project would pass if run using OGM.

## Requirements

Don't forget to set database credentials in [application.properties](src/main/resources/application.properties).

## Tests

Run [tests](src/test/java/com/example/relationshipbug/DefaultValuesTest.java) via `mvn clean install` or via an IDE.


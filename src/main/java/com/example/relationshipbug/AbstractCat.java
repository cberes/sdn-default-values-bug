package com.example.relationshipbug;

import org.springframework.data.neo4j.core.schema.Node;

@Node(primaryLabel = "MyAbstractCat")
public abstract class AbstractCat extends Animal {
}

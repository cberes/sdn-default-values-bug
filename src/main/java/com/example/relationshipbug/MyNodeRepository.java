package com.example.relationshipbug;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface MyNodeRepository extends Neo4jRepository<MyNode, String> {
}

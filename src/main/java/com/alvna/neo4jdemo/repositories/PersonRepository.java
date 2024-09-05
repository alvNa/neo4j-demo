package com.alvna.neo4jdemo.repositories;

import com.alvna.neo4jdemo.model.Person;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface PersonRepository extends Neo4jRepository<Person, Long> {
//    @Query("MATCH (p:Person)-[:FOLLOWS]->(f:Person) WHERE p.name = $name RETURN f")
//    List<Movie> findFollowersByName(String name);
}
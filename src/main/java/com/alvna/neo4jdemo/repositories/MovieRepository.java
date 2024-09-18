package com.alvna.neo4jdemo.repositories;

import com.alvna.neo4jdemo.model.Movie;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;

import java.util.List;
import java.util.Map;

public interface MovieRepository extends Neo4jRepository<Movie, Long> {
    @Query("""
        MATCH (m:Movie)<-[a:ACTED_IN]-(p:Person) 
        WHERE p.name = $name
        RETURN m
        ORDER BY m.released DESC""")
    List<Movie> findMoviesWithActor(String name);

    @Query("""
        MATCH (keanu:Person)-[:ACTED_IN]->(movie:Movie)
        WHERE keanu.name =~ $actor +'.*'
        WITH keanu, collect(movie{.title, .released}) AS movies
        RETURN keanu{.name, movies: movies}
        """)
    List<Map<String,Map<String,Object>>> findMoviesByActor(String actor);
}
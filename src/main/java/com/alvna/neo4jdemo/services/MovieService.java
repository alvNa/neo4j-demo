package com.alvna.neo4jdemo.services;

import com.alvna.neo4jdemo.repositories.MovieRepository;
import com.alvna.neo4jdemo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    public List<String> findPathBetweenActors(String actor1, String actor2){

        return List.of();
    }

}

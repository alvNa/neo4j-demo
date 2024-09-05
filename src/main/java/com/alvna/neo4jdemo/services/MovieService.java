package com.alvna.neo4jdemo.services;

import com.alvna.neo4jdemo.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository personRepository;
}

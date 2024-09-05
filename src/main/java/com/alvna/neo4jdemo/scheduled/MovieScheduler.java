package com.alvna.neo4jdemo.scheduled;

import com.alvna.neo4jdemo.repositories.MovieRepository;
import com.alvna.neo4jdemo.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MovieScheduler {
    //private final MovieService movieService;
    private final MovieRepository movieRepository;
    private final PersonRepository personRepository;

    @Scheduled(fixedRate = 2000)
    public void run(){
        var myMovies = movieRepository.findMoviesWithActor("Keanu Reeves");
        log.info("{} movies ", myMovies);
        var result = movieRepository.findAll();
        log.info("{} movies ",result.size());

        var persons = personRepository.findAll();
        log.info("{} persons ",persons.size());
    }
}

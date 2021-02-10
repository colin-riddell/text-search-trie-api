package com.example.textsearchservice.repositories;

import com.example.textsearchservice.models.Term;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface TermRepository extends ReactiveMongoRepository<Term, String> {
    Flux<Term> findByTermStartingWith(String prefix);
}

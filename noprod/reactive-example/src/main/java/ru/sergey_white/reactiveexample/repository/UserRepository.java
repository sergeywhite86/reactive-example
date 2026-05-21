package ru.sergey_white.reactiveexample.repository;


import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.sergey_white.reactiveexample.model.entity.User;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Mono<User> findByEmail(String email);
}
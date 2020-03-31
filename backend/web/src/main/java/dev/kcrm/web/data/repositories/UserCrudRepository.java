package dev.kcrm.web.data.repositories;

import dev.kcrm.web.data.documents.User;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface UserCrudRepository extends ReactiveCrudRepository<User, String> {

    @Query("{ 'roles': ?0}")
    Flux<User> findAllByRole(String role);
}

package dev.kcrm.web.data.repositories;

import dev.kcrm.web.data.documents.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCrudRepository extends ReactiveCrudRepository<User, String> {
}

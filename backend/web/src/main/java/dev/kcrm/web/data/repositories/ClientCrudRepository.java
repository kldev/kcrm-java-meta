package dev.kcrm.web.data.repositories;

import dev.kcrm.web.data.documents.Client;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientCrudRepository extends ReactiveCrudRepository<Client, String> {
}

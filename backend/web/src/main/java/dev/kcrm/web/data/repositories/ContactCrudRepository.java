package dev.kcrm.web.data.repositories;

import dev.kcrm.web.data.documents.Contact;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactCrudRepository extends ReactiveCrudRepository<Contact, String> {
}

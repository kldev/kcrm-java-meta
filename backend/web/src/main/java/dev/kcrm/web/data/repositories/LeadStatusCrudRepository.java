package dev.kcrm.web.data.repositories;

import dev.kcrm.web.data.documents.LeadStatus;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadStatusCrudRepository extends ReactiveCrudRepository<LeadStatus, String> {
}

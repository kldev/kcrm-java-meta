package dev.kcrm.web.service;

import dev.kcrm.web.data.documents.LeadStatus;
import dev.kcrm.web.data.repositories.LeadStatusCrudRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class LeadService {

    private static Log logger = LogFactory.getLog(LeadService.class);

    private LeadStatusCrudRepository leadStatusCrudRepository;

    @Autowired
    public LeadService(LeadStatusCrudRepository leadStatusCrudRepository) {
        this.leadStatusCrudRepository = leadStatusCrudRepository;
    }

    public Flux<LeadStatus> getLeadTypes() {
        return leadStatusCrudRepository.findAll();
    }
}

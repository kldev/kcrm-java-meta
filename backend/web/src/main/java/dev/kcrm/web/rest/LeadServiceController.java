package dev.kcrm.web.rest;

import dev.kcrm.web.dto.TypeDto;
import dev.kcrm.web.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("api/lead")
public class LeadServiceController {

    private LeadService service;

    @Autowired
    public LeadServiceController(LeadService service) {
        this.service = service;
    }

    @GetMapping(path = "types", produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<TypeDto> getTypes() {
        return service.getLeadTypes().map(TypeDto::FromLeadStatus);
    }
}

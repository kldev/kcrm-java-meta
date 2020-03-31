package dev.kcrm.web.service;


import dev.kcrm.web.data.documents.LeadStatus;
import dev.kcrm.web.data.documents.User;
import dev.kcrm.web.data.repositories.ClientCrudRepository;
import dev.kcrm.web.data.repositories.ContactCrudRepository;
import dev.kcrm.web.data.repositories.LeadStatusCrudRepository;
import dev.kcrm.web.data.repositories.UserCrudRepository;
import dev.kcrm.web.util.Generator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Component
public class SeedService {

    private static Log logger = LogFactory.getLog(SeedService.class);

    private UserCrudRepository userCrudRepository;
    private LeadStatusCrudRepository leadStatusCrudRepository;
    private ContactCrudRepository contactCrudRepository;
    private ClientCrudRepository clientCrudRepository;
    private Generator generator;


    @Autowired
    public SeedService(UserCrudRepository userCrudRepository,
                       LeadStatusCrudRepository leadStatusCrudRepository,
                       ContactCrudRepository contactCrudRepository,
                       ClientCrudRepository clientCrudRepository,
                       Generator generator) {

        this.userCrudRepository = userCrudRepository;
        this.leadStatusCrudRepository = leadStatusCrudRepository;
        this.contactCrudRepository = contactCrudRepository;
        this.clientCrudRepository = clientCrudRepository;
        this.generator = generator;

    }

    public void Seed() {

        SeedUsers();
        SeedLeadStatus();
    }

    private void SeedUsers() {
        long userCount = this.userCrudRepository.findAll().count().block();

        logger.info(String.format("User count %d ",userCount));

        if (userCount == 0) {

            logger.info("Seed users - start");

            User root = User.UserBuilder.anUser()
                    .withUsername("root")
                    .withPassword("toor")
                    .withEmail("root@localhost.com")
                    .withFullName("The root user").withRole("root").build();

            User admin = User.UserBuilder.anUser()
                    .withUsername("admin")
                    .withPassword("admin")
                    .withEmail("admin@localhost.com")
                    .withFullName("The admin user").withRole("admin").build();

            User john = User.UserBuilder.anUser()
                    .withUsername("john")
                    .withPassword("secret")
                    .withEmail("john.smith@localhost.com")
                    .withFullName("John Smith").withRole("user").build();

            User eve =  User.UserBuilder.anUser()
                    .withUsername("Eve")
                    .withPassword( "secret")
                    .withEmail("eve.link@localhost.com")
                    .withFullName("Eve Link").withRole("user").build();

            this.userCrudRepository.saveAll(Flux.just(root, admin, john, eve)).then().block();

            int i = 0;
            List<User> randomUsers = new ArrayList();
            while (i++ < 100)
            {
                String firstName = generator.getFaker().name().firstName();
                String lastName = generator.getFaker().name().lastName();
                String password = generator.getFaker().artist().name().trim().toLowerCase();

                User random =  User.UserBuilder.anUser()
                        .withUsername(firstName.toLowerCase())
                        .withPassword(password)
                        .withEmail(String.format("%s.%s@localhost.com", firstName.toLowerCase(), lastName.toLowerCase()))
                        .withFullName(String.format("%s %s", firstName, lastName)).withRole("user").build();

                randomUsers.add(random);

            }

            this.userCrudRepository.saveAll(randomUsers::iterator).then().block();
            logger.info("Seed users - end");
        }
        else {
            logger.info("Users already in database - skip seed step");
        }

    }

    private LeadStatus convertStringToStatus(String value) {
        return LeadStatus.LeadStatusBuilder.aLeadStatus()
                .withCode(value)
                .withDescription(String.format( "The status %s", value)).build();
    }

    private void SeedLeadStatus() {
        long leadStatusCount = leadStatusCrudRepository.count().block();

        logger.info(String.format("Lead statuses count %d ",leadStatusCount));

        if (leadStatusCount == 0) {
            logger.info("Seed lead statuses - start");
            List<String> leadStatusOption = new ArrayList<>(Arrays.asList("new", "failed", "cold", "hot", "successful"));
            leadStatusCrudRepository.saveAll(Flux.fromStream(leadStatusOption.stream()).map(this::convertStringToStatus)).then().block();
            logger.info("Seed lead statuses  - end");
        }
        else {
            logger.info("Lead statuses already in database - skip seed step");
        }
    }
}

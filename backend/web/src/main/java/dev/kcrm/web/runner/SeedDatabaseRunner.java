package dev.kcrm.web.runner;

import dev.kcrm.web.service.SeedService;
import dev.kcrm.web.util.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Component
public class SeedDatabaseRunner implements CommandLineRunner {

    private static Log logger = LogFactory.getLog(SeedDatabaseRunner.class);

    private SeedService seedService;

    @Autowired
    public SeedDatabaseRunner(SeedService seedService) {
        this.seedService = seedService;
    }

    @Override
    public void run(String... args) throws Exception {

        logger.info("Check database seeded");

        seedService.Seed();

        logger.info("Seeded database completed");

    }
}

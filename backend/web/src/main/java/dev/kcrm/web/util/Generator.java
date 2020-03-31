package dev.kcrm.web.util;

import com.github.javafaker.Faker;
import org.springframework.stereotype.Component;

@Component
public class Generator {

    private final Faker faker;

    public Generator() {
        this.faker = new Faker();
    }


    public Faker getFaker() {
        return faker;
    }
}

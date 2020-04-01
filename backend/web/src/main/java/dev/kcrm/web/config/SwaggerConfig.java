package dev.kcrm.web.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class SwaggerConfig {


@Bean
public OpenAPI customOpenAPI(@Value("${springdoc.version}") String appVersion) {
    return new OpenAPI()
            .info(new Info().title("CRM API").version(appVersion));
}

@Bean
public GroupedOpenApi storeOpenApi() {
        String[] paths = { "/api/**" };
        return GroupedOpenApi.builder().setGroup("api").pathsToMatch(paths)
        .build();
        }

}


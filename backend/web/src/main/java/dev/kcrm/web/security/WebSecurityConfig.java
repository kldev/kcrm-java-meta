package dev.kcrm.web.security;

import dev.kcrm.web.security.UnauthorizedAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/resources/**",
            "/webjars/**",
            "/authorize/**",
            "/favicon.ico",
            "/swagger-ui.html**",
            "/index.html",
            "/v3/**",
            "/api/ping",
           // "/api/**",
    };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, UnauthorizedAuthenticationEntryPoint entryPoint) {

        http.httpBasic().disable()
                .formLogin().disable()
                .csrf().disable()
                .logout().disable();

        http
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint)
                .and()
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .and()
               // .addFilterAt(webFilter(), SecurityWebFiltersOrder.AUTHORIZATION)
                .authorizeExchange()
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated();

        return http.build();
    }
}
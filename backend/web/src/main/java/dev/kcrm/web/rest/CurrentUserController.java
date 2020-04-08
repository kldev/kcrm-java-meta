package dev.kcrm.web.rest;


import dev.kcrm.web.dto.UserDto;
import dev.kcrm.web.security.WebApiUserDetails;
import dev.kcrm.web.service.LeadService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.security.Principal;
import java.util.Objects;

@RestController
@RequestMapping("api/current")
public class CurrentUserController {

    private static Log logger = LogFactory.getLog(CurrentUserController.class);

    @GetMapping(path = "", produces = "application/json")
    public Mono<UserDto > getCurrent(ServerWebExchange exchange) {
        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .filter(Objects::nonNull)
                .filter(Authentication::isAuthenticated)
                .map(Authentication::getDetails)
                .filter(Objects::nonNull)
                .cast(UserDto.class);

    }


}

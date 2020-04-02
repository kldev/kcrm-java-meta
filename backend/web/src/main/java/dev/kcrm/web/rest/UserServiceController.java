package dev.kcrm.web.rest;

import dev.kcrm.web.data.documents.User;
import dev.kcrm.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/user")
public class UserServiceController {

    private UserService service;

    @Autowired
    public UserServiceController(UserService service) {
        this.service = service;
    }

    @GetMapping(path = "/{role}", produces = "application/json")
    public Flux<User> getByRole(@PathVariable String role) {
        return service.getUserByRole(role);
    }

}

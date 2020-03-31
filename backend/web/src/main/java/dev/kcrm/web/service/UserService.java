package dev.kcrm.web.service;


import dev.kcrm.web.data.documents.User;
import dev.kcrm.web.data.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class UserService {

    private UserCrudRepository userCrudRepository;

    @Autowired
    public UserService(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    public Flux<User> getUserByRole(String role) {
        return this.userCrudRepository.findAllByRole(role);
    }
}

package dev.kcrm.web.security;

import dev.kcrm.web.data.repositories.UserCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {


    private final UserCrudRepository userCrudRepository;

    @Autowired
    public ReactiveUserDetailsServiceImpl(UserCrudRepository userCrudRepository) {
        this.userCrudRepository = userCrudRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String login) {
        return userCrudRepository.findByUsername(login)
                .filter(Objects::nonNull)
                .switchIfEmpty(Mono.error(new BadCredentialsException(String.format("User %s not found in database", login))))
                .map(WebApiUserDetails::convertFrom);
    }


}

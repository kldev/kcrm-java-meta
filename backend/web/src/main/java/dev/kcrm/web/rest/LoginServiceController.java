package dev.kcrm.web.rest;

import dev.kcrm.web.dto.LoginRequestDto;
import dev.kcrm.web.dto.TokenDto;
import dev.kcrm.web.security.jwt.JwtProvider;
import dev.kcrm.web.security.jwt.JwtReactiveAuthenticationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.Validator;

@RestController
@RequestMapping("/api/login")
public class LoginServiceController {

    private final JwtProvider tokenProvider;
    private final JwtReactiveAuthenticationManager authenticationManager;
    private final Validator validation;

    @Autowired
    public LoginServiceController(JwtProvider tokenProvider, JwtReactiveAuthenticationManager authenticationManager, Validator validation) {
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
        this.validation = validation;
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = { MediaType.APPLICATION_JSON_VALUE})
    public Mono<TokenDto> authorize(@Valid @RequestBody LoginRequestDto loginRequestDto) {
        if (!this.validation.validate(loginRequestDto).isEmpty()) {
            return Mono.error(new RuntimeException("Bad request"));
        }

        /// create authentication
        Authentication authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());

        /// authenticate
        Mono<Authentication> authentication = this.authenticationManager.authenticate(authenticationToken);
        authentication.doOnError(throwable -> {
            throw new BadCredentialsException("Username or password invalid");
        });
        ReactiveSecurityContextHolder.withAuthentication(authenticationToken);

        return authentication.map(auth -> {
            String jwt = tokenProvider.createToken(auth);
            return new TokenDto(jwt);
        });
    }
}

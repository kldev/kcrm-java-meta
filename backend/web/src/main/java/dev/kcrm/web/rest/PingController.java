package dev.kcrm.web.rest;

import dev.kcrm.web.dto.PongResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("api/ping")
public class PingController {

    @GetMapping("")
    private Mono<PongResponse> getPing() {
        return Mono.just(new PongResponse("Pong"));
    }
}

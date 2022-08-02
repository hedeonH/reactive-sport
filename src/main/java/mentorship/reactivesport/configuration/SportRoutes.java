package mentorship.reactivesport.configuration;

import io.netty.util.internal.StringUtil;
import mentorship.reactivesport.service.SportService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromValue;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@EnableWebFlux
public class SportRoutes {

    private final SportService sportService;

    public SportRoutes(SportService sportService) {
        this.sportService = sportService;
    }

    @Bean
    RouterFunction<ServerResponse> home() {
        return route(GET("/"), request -> ok().body(fromValue("Home page")));
    }

    @Bean
    RouterFunction<ServerResponse> sportOperations() {
        return route(POST("/api/v1/sport/{sportName}"),
                request ->
                        sportService.createSport(request.pathVariable("sportName"))
                                .flatMap(sport -> ok()
                                        .contentType(APPLICATION_JSON)
                                        .bodyValue(sport)
                                )
        ).andRoute(GET("/api/v1/sport"),
                request ->
                        sportService.findSports(
                                        List.of(request.queryParam("q").orElse(StringUtil.EMPTY_STRING)
                                        )
                                )
                                .collectList()
                                .flatMap(sportDtos -> ok().contentType(APPLICATION_JSON).bodyValue(sportDtos))
        );
    }
}

package mentorship.reactivesport.service;

import mentorship.reactivesport.document.SportDocument;
import mentorship.reactivesport.dto.SportDto;
import mentorship.reactivesport.mapper.SportMapper;
import mentorship.reactivesport.repository.SportRepository;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import org.springframework.web.reactive.function.client.ExchangeFunctions;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
public class SportService {

    private final SportRepository sportRepository;

    private final SportMapper sportMapper;

    private final ExchangeFunction exchange = ExchangeFunctions.create(new ReactorClientHttpConnector());

    public SportService(SportRepository sportRepository, SportMapper sportMapper) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
    }

    public Mono<SportDto> createSport(String sportName) {
        return sportRepository.existsByName(sportName).flatMap(boo -> {
            if (boo != null && boo) {
                return sportRepository.save(SportDocument.builder().name(sportName).build()).map(sportMapper::toDTO);
            } else {
                return Mono.error(new RuntimeException());
            }
        });
    }

    public Flux<SportDto> findSports(List<String> sportNames) {
        return sportRepository.findAllByNameIn(sportNames).map(sportMapper::toDTO);
    }

    public Flux<SportDto> populateSports() {
        URI uri = URI.create("https://sports.api.decathlon.com/sports");
        ClientRequest request = ClientRequest.create(HttpMethod.GET, uri).build();
        return sportRepository.saveAll(
                        exchange.exchange(request)
                                .flatMapMany(response -> response.bodyToFlux(SportDto.class)
                                        .map(sportMapper::toDocument)))
                .map(sportMapper::toDTO);
    }
}

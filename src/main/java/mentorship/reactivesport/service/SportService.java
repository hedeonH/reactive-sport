package mentorship.reactivesport.service;

import mentorship.reactivesport.document.SportDocument;
import mentorship.reactivesport.dto.SportAWS;
import mentorship.reactivesport.dto.SportDto;
import mentorship.reactivesport.mapper.SportMapper;
import mentorship.reactivesport.repository.SportRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SportService {

    public static final String URI = "http://localhost:3008/sports";
    private final SportRepository sportRepository;

    private final SportMapper sportMapper;

    private final WebClient webClient;

    public SportService(SportRepository sportRepository, SportMapper sportMapper, WebClient webClient) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
        this.webClient = webClient;
    }

    public Mono<SportDto> createSport(String sportName) {
        return sportRepository.existsByName(sportName).flatMap(boo -> {
            if (boo != null && !boo) {
                return sportRepository.save(SportDocument.builder().name(sportName).build()).map(sportMapper::toDTO);
            } else {
                return Mono.error(new RuntimeException());
            }
        });
    }

    public Flux<SportDto> findSports(List<String> sportNames) {
        return sportRepository.findAllByNameIn(sportNames).map(sportMapper::toDTO);
    }

    public List<SportDto> populateSports() {
        return webClient.get()
                .uri(URI)
                .retrieve()
                .bodyToFlux(SportAWS.class)
                .map(sportMapper::toDocument)
                .flatMap(sportRepository::save)
                .map(sportMapper::toDTO)
                .collectList()
                .block();
    }
}

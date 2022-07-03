package mentorship.reactivesport.service;

import mentorship.reactivesport.document.SportDocument;
import mentorship.reactivesport.dto.SportDto;
import mentorship.reactivesport.mapper.SportMapper;
import mentorship.reactivesport.repository.SportRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class SportService {

    private final SportRepository sportRepository;

    private final SportMapper sportMapper;

    public SportService(SportRepository sportRepository, SportMapper sportMapper) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
    }

    public Mono<SportDto> createSport(String sportName){
        return sportRepository.existsByName(sportName).flatMap(boo-> {
            if (boo != null && boo) {
                return sportRepository.save(SportDocument.builder().name(sportName).build()).map(sportMapper::toDTO);
            }else {
                return Mono.error(new RuntimeException());
            }
        });
    }

    public Flux<SportDto> findSports(List<String> sportNames){
        return sportRepository.findAllByNameIn(sportNames).map(sportMapper::toDTO);
    }
}

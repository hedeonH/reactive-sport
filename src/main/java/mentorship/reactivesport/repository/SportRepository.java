package mentorship.reactivesport.repository;

import mentorship.reactivesport.document.SportDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface SportRepository extends ReactiveCrudRepository<SportDocument, Integer> {

    Mono<Boolean> existsByName(String name);

    Flux<SportDocument> findAllByNameIn(List<String> names);

}


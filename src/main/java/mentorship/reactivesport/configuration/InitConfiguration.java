package mentorship.reactivesport.configuration;

import mentorship.reactivesport.service.SportService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InitConfiguration {

    private final SportService sportService;

    public InitConfiguration(SportService sportService) {
        this.sportService = sportService;
    }

    @Bean
    InitializingBean populateDatabase() {
        return sportService::populateSports;
    }
}


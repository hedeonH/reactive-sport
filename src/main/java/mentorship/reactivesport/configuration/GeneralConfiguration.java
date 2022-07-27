package mentorship.reactivesport.configuration;

import mentorship.reactivesport.service.SportService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfiguration {

    private final SportService sportService;

    public GeneralConfiguration(SportService sportService) {
        this.sportService = sportService;
    }

    @Bean
    InitializingBean populateDatabase() {
        return sportService::populateSports;
    }
}


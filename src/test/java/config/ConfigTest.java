package config;

import org.springframework.context.annotation.Bean;
import service.GameApiService;
import service.GameService;

/**
 * Created by pcorentin on 08/07/2015.
 */
public class ConfigTest {

    @Bean
    public GameApiService gameApiService() {
        return new GameApiService();
    }

    @Bean
    public GameService gameService() {
        return new GameService();
    }


}
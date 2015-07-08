package config;

import application.Main;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import service.GameApiService;
import service.GameService;

import javax.ws.rs.ext.RuntimeDelegate;
import java.util.Arrays;

/**
 * Created by pcorentin on 08/07/2015.
 */
@Configuration
public class AppConfiguration {

    @Bean(destroyMethod = "shutdown")
    public SpringBus cxf() {
        return new SpringBus();
    }

    @Bean
    public Server Server() {
        JAXRSServerFactoryBean factory = RuntimeDelegate.getInstance().createEndpoint(apiApplication(), JAXRSServerFactoryBean.class);
        factory.setServiceBeans(Arrays.<Object>asList(gameApiService()));
        factory.setAddress("/" + factory.getAddress());
        factory.setProviders(Arrays.<Object>asList(jsonProvider()));
        return factory.create();
    }

    @Bean
    public Main apiApplication() {
        return new Main();
    }

    @Bean
    public GameApiService gameApiService() {
        return new GameApiService();
    }

    @Bean
    public GameService gameService() {
        return new GameService();
    }

    @Bean
    public JacksonJsonProvider jsonProvider() {
        return new JacksonJsonProvider();
    }

}

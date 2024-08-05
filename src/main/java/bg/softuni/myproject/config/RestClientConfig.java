package bg.softuni.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("genericRestClient")
    public RestClient genericRestClient(){
        return RestClient.create();
    }
}

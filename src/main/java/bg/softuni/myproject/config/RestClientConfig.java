package bg.softuni.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean("genericRestClient")
    public RestClient genericRestClient(){
        return RestClient.create();
    }

    @Bean("trainingsRestClient")
    public RestClient trainingsRestClient(TrainingApiConfig trainingApiConfig
                                          ){

        return RestClient
                .builder()
                .baseUrl(trainingApiConfig.getBaseUrl())
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

}

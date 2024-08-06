package bg.softuni.myproject.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "trainings.api")
public class TrainingApiConfig {

    private String baseUrl;

    public String getBaseUrl() {
        return baseUrl;
    }

    public TrainingApiConfig setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }
}

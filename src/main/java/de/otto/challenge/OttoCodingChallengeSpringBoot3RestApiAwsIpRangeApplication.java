package de.otto.challenge;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OttoCodingChallengeSpringBoot3RestApiAwsIpRangeApplication {

    public static void main(String[] args) {
        SpringApplication.run(OttoCodingChallengeSpringBoot3RestApiAwsIpRangeApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
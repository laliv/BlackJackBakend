package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

@ConfigurationProperties
public class BlackJackProperties {

    @Value("${blackjack.file}")
    private String file;

    @Value("${blackjack.url}")
    private String url;

    private int number;

    @Bean
    @Primary
    public String getFile() {
        return file;
    }

    @Bean
    public String getUrl() {
        return url;
    }

    @Bean
    public int getNumber() {
        return number;
    }

}

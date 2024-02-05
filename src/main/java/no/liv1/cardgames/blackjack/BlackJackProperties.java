package no.liv1.cardgames.blackjack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

//@Configuration
//@PropertySource("classpath:application.properties")
@Configuration
@ConfigurationProperties(prefix = "blackjack")
public class BlackJackProperties {

    @Value("blackjack.url")
    private String url;

    @Bean
    public String getShuffleUrl() {
        return url;
    }
}

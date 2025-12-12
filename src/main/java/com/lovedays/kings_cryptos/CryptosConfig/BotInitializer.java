package com.lovedays.kings_cryptos.CryptosConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import jakarta.annotation.PostConstruct;

@Configuration
public class BotInitializer {

    
   private final KingsCryptos kingsCryptos;

   
    public BotInitializer(KingsCryptos kingsCryptos) {
    this.kingsCryptos = kingsCryptos;
}

    @PostConstruct
    public void init() {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(kingsCryptos);
            System.out.println(" KingsCryptos bot successfully registered!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}

package com.lovedays.kings_cryptos.Services;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
@Service
public class CryptoServices implements CryptoServiceIMPL {

    private final WebClient webClient;

    public CryptoServices(WebClient webClient) {
        this.webClient = WebClient.create("https://api.coingecko.com/api/v3");
    }

    @Override
    public List<Map<String, Object>> getMarketData() {
            return webClient.get()
                    .uri("/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=20&page=1&sparkline=false")
                    .retrieve()
                    .bodyToMono(List.class)
                    .block();
       
    }
    }

    


   

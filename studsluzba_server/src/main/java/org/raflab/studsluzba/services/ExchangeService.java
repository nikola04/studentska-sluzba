package org.raflab.studsluzba.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ExchangeService {
    private final RestTemplate restTemplate = new RestTemplate();

    private static final String API_URL = "https://kurs.resenje.org/api/v1/currencies/eur/rates/today";

    public Double getEuroRate() {
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(API_URL, Map.class);
            Map body = response.getBody();

            if (body != null && body.containsKey("exchange_middle")) {
                return ((Number) body.get("exchange_middle")).doubleValue();
            } else {
                throw new RuntimeException("API responded without 'exchange_middle' key.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

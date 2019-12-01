package com.bt.au.shoppingcart.controllers;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("wit")
public class WitController {

    private static final Logger LOGGER = LoggerFactory.getLogger(WitController.class);

    private static final String STORAGE_KEY = "STORAGE";
    private static final String MODEL_KEY = "MODEL";

    @Value("${witAiAuthorization}")
    private String AUTHORIZATION;

    @Value("${endpoint}")
    private String endpoint;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> wit(@RequestParam("msg") String msg) throws UnsupportedEncodingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION);
        headers.setContentType(MediaType.APPLICATION_JSON);

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString());
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<String> response = restTemplate.exchange(endpoint + msg, HttpMethod.GET, entity, String.class);

        String body = response.getBody();

        JSONObject jsonObject = new JSONObject(body);
        Map<String, String> mapEntities = (Map)jsonObject.toMap().get("entities");

        Map<String, String> entities = (Map)jsonObject.toMap().get("entities");

        LOGGER.info("STORAGE {}", entities.get(STORAGE_KEY));
        LOGGER.info("MODEL {}", entities.get(MODEL_KEY));
        LOGGER.info("ENTITIES {}", entities);

        return response;


    }
}

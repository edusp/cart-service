package com.bt.au.shoppingcart.controllers;

import com.bt.au.shoppingcart.model.wit.IntentAI;
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

    public WitController() {
        System.out.println("");
    }

    @CrossOrigin(origins = "*")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IntentAI> wit(@RequestParam("msg") String msg) throws UnsupportedEncodingException {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", AUTHORIZATION);
        headers.setContentType(MediaType.APPLICATION_JSON);

        msg = URLEncoder.encode(msg, StandardCharsets.UTF_8.toString());
        HttpEntity entity = new HttpEntity(headers);

        ResponseEntity<IntentAI> responseEntity = restTemplate.exchange(endpoint + msg, HttpMethod.GET, entity,
                IntentAI.class);

       // buildResponse(new JSONObject(responseEntity.getBody()));




        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        LOGGER.info("RESPONSE {}", responseEntity.getBody());

        return ResponseEntity.ok().body(responseEntity.getBody());
    }


    private String buildResponse(JSONObject jsonObject) {

        Map<String, String> mapEntities = (Map)jsonObject.toMap().get("entities");

        LOGGER.info("STORAGE {}", mapEntities.get(STORAGE_KEY));
        LOGGER.info("MODEL {}", mapEntities.get(MODEL_KEY));
        LOGGER.info("ENTITIES {}", mapEntities);

        return null;
    }
}

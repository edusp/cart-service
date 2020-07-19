package com.bt.au.shoppingcart.controllers;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app")
public class AppController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> appGet() {
        return ResponseEntity.ok("Working");
    }

    @PostMapping("{text}")
    public ResponseEntity<String> postText(@PathVariable(value = "text") String text) {
        return ResponseEntity.ok(text);
    }

}

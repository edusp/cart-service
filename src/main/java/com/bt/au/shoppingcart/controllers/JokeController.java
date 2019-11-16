package com.bt.au.shoppingcart.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("who")
public class JokeController {


    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable("name") String name) {

        name = name.toUpperCase();

        if ("gabriel".equalsIgnoreCase(name)) {
            return ResponseEntity.ok("<h1>"+name + " Mané ! </h1>");
        }
        else if ("raquel".equalsIgnoreCase(name)) {
            return ResponseEntity.ok("<h1>"+name + " Fuinha ! </h1>");
        }else if ("bianca".equalsIgnoreCase(name)) {
            return ResponseEntity.ok("<h1>"+name + " PiuPiu ! </h1>");
        }else if ("edu".equalsIgnoreCase(name)) {
            return ResponseEntity.ok("<h1>"+name + " Very smart and Gabriel is Bafão </h1>");
        }else if ("bruna".equalsIgnoreCase(name)) {
            return ResponseEntity.ok("<h1>"+name + " Linda </h1>");
        }else {
            return ResponseEntity.ok("<h1>Try again.</h1>");
        }

    }
}

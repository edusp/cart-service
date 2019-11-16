package com.bt.au.shoppingcart.controllers;

import com.bt.au.shoppingcart.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("who")
public class JokeController {


    private UserRepository repository;

    @Autowired
    public JokeController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{name}")
    public ResponseEntity get(@PathVariable("name") String name) {

        name = name.toUpperCase();

        if ("gabriel".equalsIgnoreCase(name)) {
            return ResponseEntity.ok(name + " Mané !");
        }
        else if ("raquel".equalsIgnoreCase(name)) {
            return ResponseEntity.ok(name + " Fuinha !");
        }else if ("bianca".equalsIgnoreCase(name)) {
            return ResponseEntity.ok(name + " PiuPiu !");
        }else if ("edu".equalsIgnoreCase(name)) {
            return ResponseEntity.ok(name + " Very smart and Gabriel is Bafão");
        }else if ("bruna".equalsIgnoreCase(name)) {
            return ResponseEntity.ok(name + " Linda");
        }else {
            return ResponseEntity.ok("Try again.");
        }

    }
}

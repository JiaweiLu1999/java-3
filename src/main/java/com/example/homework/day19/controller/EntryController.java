package com.example.homework.day19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/entries")
public class EntryController {


    @GetMapping
    public ResponseEntity<?> getAllEntries() {
        return null;
    }

    @GetMapping(params = "Auth")
    public ResponseEntity<?> getEntriesByAuth(@RequestParam String Auth) {
        return null;
    }
}

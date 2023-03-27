package com.example.java3.week4.rest.demo2.controller;

import com.example.java3.week4.rest.demo2.service.WebEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/entry")
public class WebEntryController {
    private final WebEntryService webEntryService;

    @Autowired
    public WebEntryController(WebEntryService webEntryService) {
        this.webEntryService = webEntryService;
    }

    @GetMapping
    public ResponseEntity<?> getAllEntry() {
        return new ResponseEntity<>(webEntryService.getAllEntries(), HttpStatus.OK);
    }

    @GetMapping(params = {"authName"})
    public ResponseEntity<?> getAllEntryByAuth(@RequestParam String authName) {
        return new ResponseEntity<>(webEntryService.getAllEntries(authName), HttpStatus.OK);
    }
}

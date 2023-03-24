package com.example.java3.week4.rest.demo2.service;

import com.example.java3.week4.rest.demo2.domain.WebEntry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WebEntryService {
    List<WebEntry> getAllEntries();
    List<WebEntry> getAllEntries(String auth);
}

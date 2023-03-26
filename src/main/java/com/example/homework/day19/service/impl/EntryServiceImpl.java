package com.example.homework.day19.service.impl;

import com.example.homework.day19.domain.entity.Entry;
import com.example.homework.day19.service.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
public class EntryServiceImpl implements EntryService {
    private final RestTemplate restTemplate;

    @Autowired
    public EntryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Entry> findAllEntries() {
        return null;
    }

    @Override
    public List<Entry> findEntriesByAuth() {
        return null;
    }
}

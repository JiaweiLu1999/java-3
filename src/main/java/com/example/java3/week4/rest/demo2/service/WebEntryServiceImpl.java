package com.example.java3.week4.rest.demo2.service;

import com.example.java3.week4.rest.demo2.domain.WebEntry;
import com.example.java3.week4.rest.demo2.domain.WebEntryResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WebEntryServiceImpl implements WebEntryService {

    private final RestTemplate restTemplate;

    @Value("${myURL}")
    private String myURL;

    @Autowired
    public WebEntryServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<WebEntry> getAllEntries() {
        WebEntryResponseDTO data = restTemplate.getForObject(myURL, WebEntryResponseDTO.class);
        return data.getEntries();
    }

    @Override
    public List<WebEntry> getAllEntries(String auth) {
        WebEntryResponseDTO data = restTemplate.getForObject(myURL, WebEntryResponseDTO.class);
        return data.getEntries()
                .stream()
                .filter(e -> e.getAuth().equals(auth))
                .collect(Collectors.toList());
    }
}

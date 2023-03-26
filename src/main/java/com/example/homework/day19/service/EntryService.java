package com.example.homework.day19.service;

import com.example.homework.day19.domain.entity.Entry;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface EntryService {
    List<Entry> findAllEntries();
    List<Entry> findEntriesByAuth();
}

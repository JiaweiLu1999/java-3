package com.example.java3.week4.rest.demo2.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WebEntryResponseDTO {
    private int count;
    private List<WebEntry> entries;
}

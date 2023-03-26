package com.example.homework.day19.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntryResponseDTO {
    @JsonProperty("count")
    private Integer count;

    @JsonProperty("entries")
    private Collection<Entry> entries;
}


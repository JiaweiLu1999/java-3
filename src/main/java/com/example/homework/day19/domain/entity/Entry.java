package com.example.homework.day19.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Entry {
    @JsonProperty("API")
    private String API;

    @JsonProperty("Description")
    private String Description;

    @JsonProperty("Auth")
    private String Auth;

    @JsonProperty("HTTPS")
    private String HTTPS;

    @JsonProperty("Cors")
    private String Cors;

    @JsonProperty("Link")
    private String Link;

    @JsonProperty("Category")
    private String Category;
}

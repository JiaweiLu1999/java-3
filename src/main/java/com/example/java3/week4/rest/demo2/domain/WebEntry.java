package com.example.java3.week4.rest.demo2.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * "API": "Cataas",
 * "Description": "Cat as a service (cats pictures and gifs)",
 * "Auth": "",
 * "HTTPS": true,
 * "Cors": "no",
 * "Link": "https://cataas.com/",
 * "Category": "Animals"
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebEntry {
    @JsonProperty("API")
    private String API;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Auth")
    private String auth;
    @JsonProperty("HTTPS")
    private boolean HTTPS;
    @JsonProperty("Cors")
    private String Cors;
    @JsonProperty("Link")
    private String Link;
    @JsonProperty("Category")
    private String Category;

}

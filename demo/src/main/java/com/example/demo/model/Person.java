package com.example.demo.model;

import java.util.UUID;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {

    private final UUID id;
    @NotBlank
    private final String name;
    
    public Person(@JsonProperty("id") UUID id, @JsonProperty("name") String name){
        this.id = id;
        this.name = name;
    }
    /**
     * @return the id
     */
    public UUID getId() {
        return id;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
}
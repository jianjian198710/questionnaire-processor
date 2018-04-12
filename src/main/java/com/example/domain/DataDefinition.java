package com.example.domain;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
public @Data class DataDefinition implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    @Id
    private String id;
    private String name;
    private String ztype;
}

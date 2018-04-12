package com.example.domain;

import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
public @Data class Questionnaire
{
    @Id
    private ObjectId id;
    private String name;
    private String templateId;
    private Map<Long,List<Section>> content; 
}

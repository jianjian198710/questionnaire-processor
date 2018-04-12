package com.example.mapping;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
public @Data class DataGeneral
{
    @Id
    private ObjectId id;
    private ObjectId questionnaireId;
    private long templateVersion;
    //Map<sectionId,DataSection>
    private Map<String,DataSection> content;

}

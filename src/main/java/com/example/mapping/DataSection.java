package com.example.mapping;

import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

import com.example.domain.Answer;

import lombok.Data;

public @Data class DataSection
{
    //Map<QuestionId,Answer>>
    private Map<String,Answer> fieldMap = new HashMap<>();
    private ObjectId dataGeneralId;
    private String sectionId;
}

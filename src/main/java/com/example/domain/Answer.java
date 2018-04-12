package com.example.domain;

import java.util.List;
import java.util.Map;

import lombok.Data;

public @Data class Answer
{
    private String questionId;
    private String customField;
    private String customFieldType;
    //Map<itemId,List<selectionKey>>>
    private Map<String,List<String>> content;
}

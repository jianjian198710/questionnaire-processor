package com.example.domain;

import java.util.List;

import org.bson.types.ObjectId;

import lombok.Data;

public @Data class Section
{
    private String id;
    private String desc;
    private long questionnaireVersion;
    private ObjectId questionnaireId;
    private List<Question> questionList;
}

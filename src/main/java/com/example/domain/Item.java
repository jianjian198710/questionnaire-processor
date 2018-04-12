package com.example.domain;

import java.util.Map;

import lombok.Data;

public @Data class Item
{
    private String id;
    private String type;
    private String tooltip;
    private String parentId;
    private String questionId;
    private String desc;
    private String selectionKey;
    private String operation;
    private String wordContent;
    private Map<String,String> condition;
}

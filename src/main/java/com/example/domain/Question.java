package com.example.domain;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public @Data class Question
{
    private String id;
    private String sectionid;
    private String desc;
    private String tooltip;
    private String parentId;
    private List<Item> itemList;
    private Map<String,String> condition;
}

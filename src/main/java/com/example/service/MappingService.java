package com.example.service;

import java.util.Map;

import com.example.domain.DataDefinition;
import com.example.domain.Questionnaire;
import com.example.mapping.DataGeneral;

public interface MappingService
{
    public DataGeneral createQuestionnaireMapping(Questionnaire questionnaire, Long templateVersion, Map<String,DataDefinition> customFieldMap);
    public DataGeneral updateQuestionnaireMapping(Questionnaire questionnaire, Long templateVersion, Map<String,DataDefinition> customFieldMap);
    
    //only use for test
    public Map<String, DataDefinition> generateInputMap(Questionnaire questionnaire, Long templateVersion);
}

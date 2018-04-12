package com.example.service;

import org.bson.types.ObjectId;

import com.example.domain.Questionnaire;

public interface QuestionnaireService
{
    public Questionnaire createQuestionnaire(Long templateVersion);
    public Questionnaire getQuestionnaire(ObjectId questionnaireId);
    public Questionnaire answerQuestionnaire(ObjectId questionnaireId, Long templateVersion) throws Exception;
    public Questionnaire generateQuestionnaire(String jsonStr);
}

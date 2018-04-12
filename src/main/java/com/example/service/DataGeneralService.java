package com.example.service;

import org.bson.types.ObjectId;

import com.example.mapping.DataGeneral;

public interface DataGeneralService
{
    public DataGeneral findByQuestionnaireIdAndTemplateVersion(ObjectId questionnaireId, Long templateVersion);
}

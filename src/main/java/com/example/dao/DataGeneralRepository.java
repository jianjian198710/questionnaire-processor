package com.example.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.mapping.DataGeneral;

public interface DataGeneralRepository extends MongoRepository<DataGeneral, String>
{
    public DataGeneral findByQuestionnaireIdAndTemplateVersion(ObjectId questionnaireId, Long templateVersion);
}

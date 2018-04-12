package com.example.dao;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.domain.Questionnaire;

public interface QuestionnaireRepository extends MongoRepository<Questionnaire, ObjectId>
{

}

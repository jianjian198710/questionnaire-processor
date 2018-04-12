package com.example.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.QuestionnaireRepository;
import com.example.datafactory.QuestionnaireFactory;
import com.example.domain.Questionnaire;
import com.example.domain.Section;
import com.example.service.QuestionnaireService;
import com.google.gson.Gson;

@Service
public class QuestionnaireServiceImpl implements QuestionnaireService
{
    
    @Autowired
    private QuestionnaireRepository questionnaireRepo;
    
    @Override
    public Questionnaire createQuestionnaire( Long templateVersion )
    {
        if(Objects.isNull(templateVersion)){
            templateVersion = 1L;
        }
        Questionnaire questionnaire = QuestionnaireFactory.generateQuestionnare(templateVersion);
        questionnaire = QuestionnaireFactory.generateQuestionnareAnswer(questionnaire, templateVersion);
//        Questionnaire questionnaire = null;
//        try {
//            questionnaire = QuestionnaireFactory.generateFromJson(null);
//        }
//        catch( FileNotFoundException e ) {
//            e.printStackTrace();
//        }
        return questionnaireRepo.save(questionnaire);
    }

    @Override
    public Questionnaire getQuestionnaire( ObjectId questionnaireId )
    {
        return questionnaireRepo.findOne(questionnaireId);
    }

    @Override
    public Questionnaire answerQuestionnaire( ObjectId questionnaireId, Long templateVersion ) throws Exception
    {
        Questionnaire questionnaire = questionnaireRepo.findOne(questionnaireId);
        if(Objects.isNull(questionnaire)){
            throw new Exception("questionniare is not existed!");
        }
        if(Objects.isNull(templateVersion)){
            templateVersion = 1L;
        }
        questionnaire =  QuestionnaireFactory.generateQuestionnareAnswer(questionnaire, templateVersion);
        return questionnaireRepo.save(questionnaire);
    }
    
    @Override
    public Questionnaire generateQuestionnaire( String jsonStr ){
        Gson gson = new Gson();
//        JsonReader reader = new JsonReader(new FileReader("src/main/resources/file/questionnaire.json"));
        Questionnaire generatedQuestionnaire =  gson.fromJson(jsonStr,Questionnaire.class);
        Questionnaire questionnaire = questionnaireRepo.findOne(generatedQuestionnaire.getId());{
            if(Objects.isNull(questionnaire)){
                return questionnaireRepo.save(generatedQuestionnaire);
            }else{
                //only update related version
                for(Map.Entry<Long, List<Section>> entry: generatedQuestionnaire.getContent().entrySet()){
                    questionnaire.getContent().put(entry.getKey(),entry.getValue());
                }
                return questionnaireRepo.save(questionnaire);
            }
        }
    }

}

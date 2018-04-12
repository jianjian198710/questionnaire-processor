package com.example.web;

import java.io.FileNotFoundException;
import java.util.Map;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.datafactory.QuestionnaireFactory;
import com.example.datafactory.QuestionnaireRemote;
import com.example.domain.DataDefinition;
import com.example.domain.Questionnaire;
import com.example.mapping.DataGeneral;
import com.example.service.MappingService;
import com.example.service.QuestionnaireService;

@RestController
@RequestMapping(value = "/questionnaire")
public class QuestionnaireController
{
    @Autowired
    private QuestionnaireService questionnaireService;
    
    @Autowired
    private MappingService mappingService;

    /*
     * only for test
     */
    @GetMapping(value = "/create/{templateVersion}")
    public Questionnaire createQuestionnaire(@PathVariable Long templateVersion){
        return questionnaireService.createQuestionnaire(templateVersion);
    }
    
    
    @GetMapping(value="/get/{questionnaireId}")
    public Questionnaire getQuestionnaire(@PathVariable ObjectId questionnaireId){
        return questionnaireService.getQuestionnaire(questionnaireId);
    }
    
    @GetMapping(value="/answer/{questionnaireId}/{templateVersion}")
    public Questionnaire answerQuestionnaire(@PathVariable ObjectId questionnaireId, @PathVariable Long templateVersion) throws Exception{
        return questionnaireService.answerQuestionnaire(questionnaireId, templateVersion);
    }
    
    
    
    @PostMapping(value = "/mapping/create/{questionnaireId}/{templateVersion}",produces="application/json")
    public DataGeneral createQuestionnaireMapping(@PathVariable ObjectId questionnaireId,@PathVariable Long templateVersion, 
        @RequestParam(value = "customFieldMapping", required = false) Map<String,DataDefinition> customFieldMap) throws Exception{

        Questionnaire questionnaire = null;
        //should call remote service, get json string
        questionnaire = questionnaireService.getQuestionnaire(questionnaireId);
        if(Objects.isNull(templateVersion)){
            throw new Exception("templateVersion is null");
        }
        
        if(Objects.isNull(questionnaire)){
            throw new Exception("questionnaire is null");
        }
        
        if(Objects.isNull(customFieldMap)){
            customFieldMap = mappingService.generateInputMap(questionnaire, templateVersion);
        }
        
        return mappingService.createQuestionnaireMapping(questionnaire, templateVersion, customFieldMap);
    }
    
    @PutMapping(value = "/mapping/update/{questionnaireId}/{templateVersion}",produces="application/json")
    public DataGeneral updateQuestionnaireMapping(@PathVariable ObjectId questionnaireId,@PathVariable Long templateVersion, 
        @RequestParam(value = "customFieldMapping", required = false) Map<String,DataDefinition> customFieldMap) throws Exception{
        
        Questionnaire questionnaire = null;
        //should call remote service, get json string
        questionnaire = questionnaireService.getQuestionnaire(questionnaireId);
        if(Objects.isNull(templateVersion)){
            throw new Exception("templateVersion is null");
        }
        
        if(Objects.isNull(questionnaire)){
            throw new Exception("questionnaire is null");
        }
        
        if(Objects.isNull(customFieldMap)){
            customFieldMap = mappingService.generateInputMap(questionnaire, templateVersion);
        }
        
        return mappingService.updateQuestionnaireMapping(questionnaire, templateVersion, customFieldMap);
    }
    
    @GetMapping(value="/testJson")
    public Questionnaire jsonTest() throws FileNotFoundException{
        return QuestionnaireFactory.generateFromJson(null);
    }
    
    @GetMapping(value="/generate/{jsonArrayIndex}")
    public Questionnaire generateQuestionnaire(@PathVariable int jsonArrayIndex){
        return questionnaireService.generateQuestionnaire(QuestionnaireRemote.QUESTIONNAIRE_JSON_COLLECTION[jsonArrayIndex]);
    }

}

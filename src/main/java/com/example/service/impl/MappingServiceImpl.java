package com.example.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.dao.DataGeneralRepository;
import com.example.domain.Answer;
import com.example.domain.DataDefinition;
import com.example.domain.Item;
import com.example.domain.Question;
import com.example.domain.Questionnaire;
import com.example.domain.Section;
import com.example.mapping.DataGeneral;
import com.example.mapping.DataSection;
import com.example.service.MappingService;
import com.example.type.ItemType;

@Service
public class MappingServiceImpl implements MappingService
{
    private static final Logger log =  LoggerFactory.getLogger(MappingServiceImpl.class);
    private static final String DATA_DEFINITION_REMOTE_URL = "http://localhost:8081/dataDefinition/get/all";
    private static final Random random = new Random();
    
    @Autowired
    private DataGeneralRepository dataGeneralRepo;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public DataGeneral createQuestionnaireMapping(Questionnaire questionnaire, Long templateVersion, Map<String,DataDefinition> customFieldMap){
        ObjectId id = new ObjectId();
        log.info("dataGeneral Id:{}",id);
        DataGeneral dataGeneral  = new DataGeneral();
        dataGeneral.setId(id);
        dataGeneral.setQuestionnaireId(questionnaire.getId());
        dataGeneral.setTemplateVersion(templateVersion);
        Map<String,DataSection> dataGeneralContent = new HashMap<>();
        for(Section section : questionnaire.getContent().get(templateVersion)){
            DataSection dataSection = this.createSectionMapping(section,customFieldMap);
            dataSection.setDataGeneralId(id);
            dataGeneralContent.put(section.getId(), dataSection);
        }
        dataGeneral.setContent(dataGeneralContent);
        return dataGeneralRepo.save(dataGeneral);
    }
    
    @Override
    public DataGeneral updateQuestionnaireMapping(Questionnaire questionnaire, Long templateVersion, Map<String,DataDefinition> customFieldMap){
        DataGeneral dataGeneral = dataGeneralRepo.findByQuestionnaireIdAndTemplateVersion(questionnaire.getId(), templateVersion);
        if(Objects.isNull(dataGeneral)){
            try {
                throw new Exception("No dataGeneral existed!");
            }
            catch( Exception e ) {
                e.printStackTrace();
            }
        }
        dataGeneral.getContent().clear();
        Map<String,DataSection> dataGeneralContent = new HashMap<>();
        for(Section section : questionnaire.getContent().get(templateVersion)){
            DataSection dataSection = this.createSectionMapping(section,customFieldMap);
            dataSection.setDataGeneralId(dataGeneral.getId());
            dataGeneralContent.put(section.getId(), dataSection);
        }
        dataGeneral.setContent(dataGeneralContent);
        return dataGeneralRepo.save(dataGeneral);
    }
    
    private DataSection createSectionMapping(Section section,Map<String,DataDefinition> inputMap){
       DataSection dataSection = new DataSection();
       dataSection.setSectionId(section.getId());
       Map<String,Answer> fieldMap = new HashMap<>();
       for(Question question: section.getQuestionList()){
           Answer answer = new Answer();
           answer.setQuestionId(question.getId());
           //fill answer custom field
           DataDefinition dataDefinition = inputMap.get(question.getId());
           answer.setCustomField(dataDefinition.getName());
           answer.setCustomFieldType(dataDefinition.getZtype());
           //fill answer content
           Map<String,List<String>> answerContent = new HashMap<>();
           answer.setContent(answerContent);
           answer = this.getItemAnswer(question.getItemList(), answer);
           fieldMap.put(question.getId(), answer);
       }
       dataSection.setFieldMap(fieldMap);
       return dataSection;
    }
    
    private Answer getItemAnswer(List<Item> itemList, Answer answer){
        //clean old answer
        answer.getContent().clear();
        for(Item item: itemList){
            if(Objects.nonNull(item.getOperation())){
                if(item.getType().equals(ItemType.SELECTION_ITEM.getName())){
                    List<String> keys = new ArrayList<>();
                    keys.add(item.getSelectionKey());
                    answer.getContent().put(item.getId(),keys);
                }
                
                if(item.getType().equals(ItemType.COMPLETION.getName())){
                    List<String> keys = new ArrayList<>();
                    //TODO for multinput,split the keys;
                    keys.add(item.getWordContent());
                    answer.getContent().put(item.getId(),keys);
                }
            }
        }
        return answer;
    }
    
    /*
     * generate filed input
     * @Return
     * Map<questionId,customField>
     */
    @Override
    public Map<String,DataDefinition> generateInputMap(Questionnaire questionnaire, Long templateVersion){
        List<DataDefinition> dataDefinitionList = this.getRemoteDataDefintion(DATA_DEFINITION_REMOTE_URL);
        Map<String,DataDefinition> inputMap = new HashMap<>();
        for(Section section: questionnaire.getContent().get(templateVersion)){
            for(Question question: section.getQuestionList()){
                inputMap.put(question.getId(),dataDefinitionList.get(random.nextInt(dataDefinitionList.size())));
            }
        }
        return inputMap;
    }
    
    private List<DataDefinition> getRemoteDataDefintion(String remoteUrl){
        DataDefinition[] dataDefinitions = restTemplate.getForObject(remoteUrl, DataDefinition[].class);
        List<DataDefinition> dataDefinitionList = Arrays.asList(dataDefinitions);
        return dataDefinitionList;
    }
}

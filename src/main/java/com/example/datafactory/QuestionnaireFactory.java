package com.example.datafactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.domain.Item;
import com.example.domain.Question;
import com.example.domain.Questionnaire;
import com.example.domain.Section;
import com.example.type.ItemType;
import com.example.type.OperationType;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class QuestionnaireFactory
{
    private static final Logger log = LoggerFactory.getLogger(QuestionnaireFactory.class);
    
    private static final String YES = "YES";
    private static final String NO = "NO";
    
    private static final String TEMPLATE_ID = "TEMPLATE_ID";
    private static final String NAME = "DATA_QUESTIONNAIRE";
    
    public static Questionnaire generateQuestionnare(Long templateVersion){
        ObjectId id = new ObjectId();
        log.info("questionniareId:{}",id);
        
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.setId(id);
        questionnaire.setName(NAME);
        questionnaire.setTemplateId(TEMPLATE_ID);
        Map<Long,List<Section>> content = new HashMap<>();
        /*
         * Section2.1
         */
        Section section2_1 = new Section();
        section2_1.setId("2-1");
        section2_1.setDesc("Data processing and data location");
        section2_1.setQuestionnaireId(questionnaire.getId());
        section2_1.setQuestionnaireVersion(templateVersion);
        
        //Q2.1.1
        Question question2_1_1 = new Question();
        question2_1_1.setId("2-1-1");
        question2_1_1.setDesc("hose personal data is processed?");
        question2_1_1.setSectionid("2-1");
        
        Item item2_1_1_1 = new Item();
        item2_1_1_1.setId("2-1-1-1");
        item2_1_1_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_1_1.setQuestionId("2-1-1");
        item2_1_1_1.setDesc("SAP employees (e.g. User ID, name, email-address)");
        item2_1_1_1.setSelectionKey("selectionKey2_1_1_1");
        
        Item item2_1_1_2 = new Item();
        item2_1_1_2.setId("2-1-1-2");
        item2_1_1_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_1_2.setParentId("2-1-1");
        item2_1_1_2.setDesc("SAP applicants (e.g. name, email-address)");
        item2_1_1_2.setSelectionKey("selectionKey2_1_1_2");
        
        List<Item> itemList2_1_1 = new ArrayList<>();
        itemList2_1_1.add(item2_1_1_1);
        itemList2_1_1.add(item2_1_1_2);
        question2_1_1.setItemList(itemList2_1_1);
        
        //Q2.1.2
        Question question2_1_2 = new Question();
        question2_1_2.setId("2-1-2");
        question2_1_2.setDesc("Which company is legally responsible for the process and legality of data processing?");
        question2_1_2.setSectionid("2-1");
        
        Item item2_1_2_1 = new Item();
        item2_1_2_1.setId("2-1-2-1");
        item2_1_2_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_1.setDesc("SAP entities");
        item2_1_2_1.setQuestionId("2-1-2");
        item2_1_2_1.setSelectionKey("selectionKey2_1_2_1");
        
        
        Item item2_1_2_2 = new Item();
        item2_1_2_2.setId("2-1-2-2");
        item2_1_2_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_2.setDesc("SAPSAP customer or partner established within the EU/EEA");
        item2_1_2_2.setQuestionId("2-1-2");
        item2_1_2_2.setSelectionKey("selectionKey2_1_2_2");
        
        
        Item item2_1_2_3 = new Item();
        item2_1_2_3.setId("2-1-2-3");
        item2_1_2_3.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_3.setDesc("SAPSAP customer or partner established within the EU/EEA");
        item2_1_2_3.setQuestionId("2-1-2");
        item2_1_2_3.setSelectionKey("selectionKey2_1_2_3");
        
        List<Item> itemList2_1_2 = new ArrayList<>();
        itemList2_1_2.add(item2_1_2_1);
        itemList2_1_2.add(item2_1_2_2);
        itemList2_1_2.add(item2_1_2_3);
        question2_1_2.setItemList(itemList2_1_2);
        
        //Q2.1.2.1.1
        Question question2_1_2_1_1 = new Question();
        question2_1_2_1_1.setId("2-1-2-1-1");
        question2_1_2_1_1.setId("2-1-2-1-1");
        question2_1_2_1_1.setParentId("2-1-2-1");
        Map<String,String> condition2_1_2_1_1 = new HashMap<>();
        condition2_1_2_1_1.put("2-1-2-1", OperationType.SELECT.getName());
        
        Item item2_1_2_1_1_1 = new Item();
        item2_1_2_1_1_1.setId("2-1-2-1-1");
        item2_1_2_1_1_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_1_1_1.setParentId("2-1-2-1");
        item2_1_2_1_1_1.setDesc(YES);
        item2_1_2_1_1_1.setSelectionKey("selectionKey2_1_2_1_1_1");
        Map<String,String> condition2_1_2_1_1_1 = new HashMap<>();
        condition2_1_2_1_1_1.put("2-1-2-1", OperationType.SELECT.getName());
        item2_1_2_1_1_1.setCondition(condition2_1_2_1_1_1);
        
        Item item2_1_2_1_1_2 = new Item();
        item2_1_2_1_1_2.setId("2-1-2-1-1-2");
        item2_1_2_1_1_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_1_1_2.setParentId("2-1-2-1-1");
        item2_1_2_1_1_2.setDesc(NO);
        item2_1_2_1_1_2.setSelectionKey("selectionKey2_1_2_1_1_2");
        Map<String,String> condition2_1_2_1_1_2 = new HashMap<>();
        condition2_1_2_1_1_2.put("2-1-2-1", OperationType.SELECT.getName());
        item2_1_2_1_1_2.setCondition(condition2_1_2_1_1_2);
        
        Item item2_1_2_1_1_2_1 = new Item();
        item2_1_2_1_1_2_1.setId("2-1-2-1-1-2-1");
        item2_1_2_1_1_2_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_2_1_1_2_1.setParentId("2-1-2-1-1-2");
        item2_1_2_1_1_2_1.setDesc("110405, Inc. (0266)");
        item2_1_2_1_1_2_1.setSelectionKey("selectionKey2_1_2_1_1_2_1");
        Map<String,String> condition2_1_2_1_1_2_1 = new HashMap<>();
        condition2_1_2_1_1_2_1.put("2-1-2-1-1-2", OperationType.SELECT.getName());
        item2_1_2_1_1_2_1.setCondition(condition2_1_2_1_1_2_1);
        
        List<Item> itemList2_1_2_1_1 = new ArrayList<>();
        itemList2_1_2_1_1.add(item2_1_2_1_1_1);
        itemList2_1_2_1_1.add(item2_1_2_1_1_2);
        itemList2_1_2_1_1.add(item2_1_2_1_1_2_1);
        question2_1_2_1_1.setItemList(itemList2_1_2_1_1);
        
        //Q2.1.4
        Question question2_1_4 = new Question();
        question2_1_4.setId("2-1-4");
        question2_1_4.setDesc("Where will the data specified in 2.1.1 be physically stored?");
        question2_1_4.setSectionid("2-1");
        question2_1_4.setTooltip("The data is accessed in countries");
        
        Item item2_1_4_1 = new Item();
        item2_1_4_1.setId("2-1-4-1");
        item2_1_4_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_4_1.setDesc("within the EU/EEA");
        item2_1_4_1.setSelectionKey("selectionKey2_1_4_1");
        item2_1_4_1.setQuestionId("2-1-4");
        
        Item item2_1_4_2 = new Item();
        item2_1_4_2.setId("2-1-4-2");
        item2_1_4_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_4_2.setDesc("outside the EU/EEA");
        item2_1_4_2.setSelectionKey("selectionKey2_1_4_2");
        item2_1_4_2.setQuestionId("2-1-4");
        
        List<Item> itemList2_1_4 = new ArrayList<>();
        itemList2_1_4.add(item2_1_4_1);
        itemList2_1_4.add(item2_1_4_2);
        question2_1_4.setItemList(itemList2_1_4);
        
        //Q2.1.6
        Question question2_1_6 = new Question();
        question2_1_6.setId("2-1-6");
        question2_1_6.setDesc("Which systems are used to collect and process the data specified in 2.1.1?");
        question2_1_6.setSectionid("2-1");
        
        Item item2_1_6_1 = new Item();
        item2_1_6_1.setId("2-1-6-1");
        item2_1_6_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_6_1.setQuestionId("2-1-6");
        item2_1_6_1.setSelectionKey("selectionKey2_1_6_1");
        item2_1_6_1.setDesc("Ariba");
        
        Item item2_1_6_2 = new Item();
        item2_1_6_2.setId("2-1-6-2");
        item2_1_6_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_1_6_2.setQuestionId("2-1-6");
        item2_1_6_2.setSelectionKey("selectionKey2_1_6_2");
        item2_1_6_2.setDesc("Other System(s)");
        
        Item item2_1_6_2_1 = new Item();
        item2_1_6_2_1.setId("2-1-6-2-1");
        item2_1_6_2_1.setType(ItemType.COMPLETION.getName());
        item2_1_6_2_1.setParentId("2-1-6-2");
        item2_1_6_2_1.setDesc("Indicate the system name and a short description of the type of system, e.g. MCP (productive Lead 2 Revenue System)");
        Map<String,String> condition2_1_6_2_1 = new HashMap<>();
        condition2_1_6_2_1.put("2-1-6-2", OperationType.SELECT.getName());
        item2_1_6_2_1.setCondition(condition2_1_6_2_1);
        
        List<Item> itemList2_1_6 = new ArrayList<>();
        itemList2_1_6.add(item2_1_6_1);
        itemList2_1_6.add(item2_1_6_2);
        itemList2_1_6.add(item2_1_6_2_1);
        question2_1_6.setItemList(itemList2_1_6);
        
        List<Question> questionList2_1 = new ArrayList<>();
        questionList2_1.add(question2_1_1);
        questionList2_1.add(question2_1_2);
        questionList2_1.add(question2_1_4);
        questionList2_1.add(question2_1_6);
        questionList2_1.add(question2_1_2_1_1);
        section2_1.setQuestionList(questionList2_1);
        
        /*
         * Section 2.2
         */
        Section section2_2 = new Section();
        section2_2.setId("2-2");
        section2_2.setDesc("Data evaluation");
        section2_2.setQuestionnaireId(questionnaire.getId());
        section2_2.setQuestionnaireVersion(templateVersion);
        
        //Q2.2.1
        Question question2_2_1 = new Question();
        question2_2_1.setId("2-2-1");
        question2_2_1.setDesc("Is the data evaluated?");
        question2_2_1.setSectionid("2-1");
        
        Item item2_2_1_1 = new Item();
        item2_2_1_1.setId("2-2-1-1");
        item2_2_1_1.setType(ItemType.SELECTION_ITEM.getName());
        item2_2_1_1.setDesc(YES);
        item2_2_1_1.setQuestionId("2-2-1");
        item2_2_1_1.setSelectionKey("selectionKey2_2_1_1");
        
        Item item2_2_1_2 = new Item();
        item2_2_1_2.setId("2-2-1-2");
        item2_2_1_2.setType(ItemType.SELECTION_ITEM.getName());
        item2_2_1_2.setDesc(NO);
        item2_2_1_2.setQuestionId("2-2-1");
        item2_2_1_2.setSelectionKey("selectionKey2_2_1_2");
        
        List<Item> itemList2_2_1 = new ArrayList<>();
        itemList2_2_1.add(item2_2_1_1);
        itemList2_2_1.add(item2_2_1_2);
        question2_2_1.setItemList(itemList2_2_1);
        
        List<Question> questionList2_2 = new ArrayList<>();
        questionList2_2.add(question2_2_1);
        section2_2.setQuestionList(questionList2_2);
        
        List<Section> sectionList = new ArrayList<>();
        sectionList.add(section2_1);
        sectionList.add(section2_2);
        
        content.put(templateVersion, sectionList);
        questionnaire.setContent(content);
        
        return questionnaire;
    }
    
    public static Questionnaire generateQuestionnareAnswer(Questionnaire questionnaire, Long templateVersion){
        List<Section> sectionList = questionnaire.getContent().get(templateVersion);
        for(Section section: sectionList){
            List<Question> questionList = section.getQuestionList();
            for(Question question: questionList){
                if(question.getId().equals("2-1-1")){
                    for(Item item: question.getItemList()){
                        if(item.getId().equals("2-1-1-1")||item.getId().equals("2-1-1-2")){
                            item.setOperation(OperationType.SELECT.getName());
                        }
                    }
                }
                
                if(question.getId().equals("2-1-2")){
                    for(Item item: question.getItemList()){
                        if(item.getId().equals("2-1-2-1")){
                            item.setOperation(OperationType.SELECT.getName());
                        }
                    }
                }
                
                if(question.getId().equals("2-1-2-1-1")){
                    for(Item item: question.getItemList()){
                        if(item.getId().equals("2-1-2-1-1-2-1")){
                            item.setOperation(OperationType.SELECT.getName());
                        }
                    }
                }
                
               if(question.getId().equals("2-1-4")){
                   for(Item item: question.getItemList()){
                       if(item.getId().equals("2-1-4-1")){
                           item.setOperation(OperationType.SELECT.getName());
                       }
                   }
               }
               
               if(question.getId().equals("2-1-6")){
                   for(Item item: question.getItemList()){
                       if(item.getId().equals("2-1-6-1")||item.getId().equals("2-1-6-2")){
                           item.setOperation(OperationType.SELECT.getName());
                       }
                       
                       if(item.getId().equals("2-1-6-2-1")){
                           item.setOperation(OperationType.FILL.getName());
                           item.setWordContent("SBUI5");
                       }
                   }
               }
               
               if(question.getId().equals("2-2-1")){
                   for(Item item: question.getItemList()){
                       if(item.getId().equals("2-2-1-2")){
                           item.setOperation(OperationType.SELECT.getName());
                       }
                   }
               }
            }
        }
        return questionnaire;
    }
    
    public static Questionnaire generateFromJson(String jsonStr) throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("src/main/resources/file/questionnaire.json"));
        Questionnaire questionnaire =  gson.fromJson(reader,Questionnaire.class);
        return questionnaire;
    }
}

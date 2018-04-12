package com.example.service.impl;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.DataGeneralRepository;
import com.example.mapping.DataGeneral;
import com.example.service.DataGeneralService;

@Service
public class DataGeneralServiceImpl implements DataGeneralService
{

    @Autowired
    private DataGeneralRepository dataGeneralRepo;
    
    @Override
    public DataGeneral findByQuestionnaireIdAndTemplateVersion( ObjectId questionnaireId, Long templateVersion )
    {
        return dataGeneralRepo.findByQuestionnaireIdAndTemplateVersion(questionnaireId, templateVersion);
    }

}

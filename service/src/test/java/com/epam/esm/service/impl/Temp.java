package com.epam.esm.service.impl;

import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import com.epam.esm.service.config.ServiceConfig;
import com.epam.esm.service.exeption.IllegalRequestParameters;
import com.epam.esm.service.exeption.IllegalRequestSortParameters;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.*;

@SpringJUnitConfig(ServiceConfig.class)

public class Temp {
    @Autowired
    CertificateService certificateService;

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        GiftCertificate giftCertificate=certificateService.findEntityById(1);
        int r=1;
    }

    @Test
    void giftcreate() throws RecourseNotExistException, RecourseExistException {
        GiftCertificate giftCertificate=certificateService.findEntityById(3);
        giftCertificate.setName("Create");
        Tag tag=new Tag();
        tag.setTagName("relax");
        Tag tag1=new Tag();
        tag1.setTagName("relax1");
        List <Tag> tagList=new ArrayList<>();
        tagList.add(tag);
        tagList.add(tag1);
        giftCertificate.setListTag(tagList);
        certificateService.add(giftCertificate);
        int i=0;
    }

    @Test
    void giftfindbyParams() throws IllegalRequestSortParameters, IllegalRequestParameters {
        Map<String,String> groupParams= new HashMap<>();
        groupParams.put("name","certificate");
        groupParams.put("description","desc");
        groupParams.put("sort","-name");
        List<GiftCertificate> certificateList=certificateService.findGiftCertificateListByParams(groupParams);
        int i=0;
    }


}

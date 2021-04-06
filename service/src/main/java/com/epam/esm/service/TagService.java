package com.epam.esm.service;

import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.exeption.RecourseExistException;


public interface TagService extends CommonService<Tag>{
    Tag add(Tag tag) throws RecourseExistException;
}

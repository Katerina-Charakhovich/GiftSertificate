package com.epam.esm.service.impl;


import com.epam.esm.dao.dao.TagDao;
import com.epam.esm.dao.dao.impl.TagDaoImpl;

import com.epam.esm.model.entity.Tag;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*@SpringJUnitConfig(ServiceConfig.class)*/
class TagServiceImplTest {
    /* @Autowired
     TagService tagServiceImpl;*/
    @InjectMocks
    private TagService tagService;
    @Mock
    private TagDao tagDao;

    @BeforeEach
    public void setUp() {
        tagDao = Mockito.mock(TagDaoImpl.class);
        tagService = new TagServiceImpl(tagDao);
    }

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        long testId = 10;
        Tag tag=new Tag();
        tag.setTagId(testId);
        tag.setTagName("test");
        Mockito.when(tagDao.findEntityById(testId)).thenReturn(Optional.of(tag));
        Tag tag1=tagService.findEntityById(testId);
        Assert.assertEquals(tag,tagService.findEntityById(testId));
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }


    @Test
    void testFindEntityByIdNegative()  {
        int testId = 10;
        Mockito.when(tagDao.findEntityById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.findEntityById(10));
    }
}
package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.model.entity.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TagDtoServiceImplTest {
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
        TagDto tagDto = new TagDto();
        tagDto.setTagId(testId);
        tagDto.setTagName("test");
        Mockito.when(tagDao.findEntityById(testId)).thenReturn(Optional.of(tagDto));
        TagDto tagDto1 = tagService.findEntityById(testId);
        assertEquals(tagDto, tagService.findEntityById(testId));
    }

    @Test
    void add() {
    }

    @Test
    void delete() {
    }

    @Test
    void testFindEntityByIdNegative() {
        int testId = 10;
        Mockito.when(tagDao.findEntityById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.findEntityById(10));
    }
}

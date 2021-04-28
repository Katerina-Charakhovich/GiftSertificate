package com.epam.esm.service.impl;

import com.epam.esm.dao.TagDao;
import com.epam.esm.dao.impl.TagDaoImpl;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class TagServiceImplTest {
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
    void testFindEntityByIdNegative() {
        int testId = 10;
        Mockito.when(tagDao.findEntityById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.findEntityById(10));
    }

    @Test
    void findPopularTag() {
        TagDto tagDto = new TagDto(1L, "popularTag");
        Mockito.when(tagDao.findPopularTag()).thenReturn(Optional.of(tagDto));
        Optional<TagDto> actual = tagService.findPopularTag();
        Optional<TagDto> expected = Optional.of(tagDto);
        assertEquals(expected, actual);
    }

    @Test
    void add() throws RecourseNotExistException, RecourseExistException {
        long tagId = 1;
        TagDto tagDto = new TagDto();
        tagDto.setTagName("createTag");
        Mockito.when(tagDao.findByName(tagDto.getTagName())).thenReturn(Optional.empty());
        TagDto createTagDto = new TagDto(12L, "createTag");
        Mockito.when(tagDao.create(tagDto)).thenReturn(createTagDto);
        TagDto actual = tagService.add(tagDto);
        TagDto expected = new TagDto(12L, "createTag");
        assertEquals(expected, actual);
    }

    @Test
    void addNegative() {
        Mockito.when(tagDao.findByName(anyString())).thenReturn(Optional.of(new TagDto()));
        TagDto tagDto = new TagDto();
        tagDto.setTagName("test");
        assertThrows(RecourseExistException.class, () -> tagService.add(tagDto));
    }

    @Test
    void deleteNegative() {
        Long tagId = 1L;
        TagDto tagDto = new TagDto(1L, "deleteTag");
        Mockito.when(tagDao.findEntityById(tagId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.delete(tagId));
    }

    @Test
    void findAll() {
        int offset = 0;
        int limit = 2;
        List<TagDto> tags = new ArrayList<>();
        tags.add(new TagDto(1L, "test1"));
        tags.add(new TagDto(2L, "test2"));
        Mockito.when(tagDao.findAll(offset, limit)).thenReturn(tags);
        List<TagDto> actual = tagService.findAll(offset, limit);
        assertEquals(tags, actual);
    }

    @Test
    void update() {
        TagDto tagDto = new TagDto(1L, "updateTag");
        assertThrows(UnsupportedOperationException.class, () -> tagService.update(tagDto));
    }
}

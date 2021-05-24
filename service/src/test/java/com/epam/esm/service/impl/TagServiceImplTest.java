package com.epam.esm.service.impl;

import com.epam.esm.dao.TagRepository;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.dao.entity.User;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.service.TagService;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
class TagServiceImplTest {
    @InjectMocks
    private TagService tagService;
    @Mock
    private TagRepository tagRepository;

    @BeforeEach
    public void setUp() {
        tagRepository = Mockito.mock(TagRepository.class);
        tagService = new TagServiceImpl(tagRepository = Mockito.mock(TagRepository.class));
    }

    @Test
    void findEntityByIdPositive() throws RecourseNotExistException {
        long testId = 10;
        Tag tag = new Tag();
        tag.setTagName("test");
        tag.setTagId(testId);
        TagDto expected = new TagDto();
        expected.setTagId(testId);
        expected.setTagName("test");
        Mockito.when(tagRepository.findById(testId)).thenReturn(Optional.of(tag));
        TagDto actual = tagService.findEntityById(testId);
        assertEquals(expected, actual);
    }

    @Test
    void testFindEntityByIdNegative() {
        long testId = 10;
        Mockito.when(tagRepository.findById(testId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.findEntityById(10));
    }

    @Test
    void findPopularTag() {
        TagDto expectedTagDto = new TagDto(1L, "popularTag");
        Tag tag = new Tag(1L, "popularTag");
        Mockito.when(tagRepository.findPopularTag()).thenReturn(Optional.of(tag));
        Optional<TagDto> actual = tagService.findPopularTag();
        Optional<TagDto> expected = Optional.of(expectedTagDto);
        assertEquals(expected, actual);
    }

    @Test
    void add() throws RecourseNotExistException, RecourseExistException {
        long tagId = 1;
        Tag tag = new Tag();
        tag.setTagName("createTag");
        Mockito.when(tagRepository.findByTagName(tag.getTagName())).thenReturn(Optional.empty());
        Tag createTag = new Tag(12L, "createTag");
        Mockito.when(tagRepository.save(tag)).thenReturn(createTag);
        TagDto tagDto = new TagDto();
        tagDto.setTagName("createTag");
        TagDto actual = tagService.add(tagDto);
        TagDto expected = new TagDto(12L, "createTag");
        assertEquals(expected, actual);
    }

    @Test
    void deleteNegative() {
        long tagId = 1L;
        TagDto tagDto = new TagDto(1L, "deleteTag");
        Mockito.when(tagRepository.findById(tagId)).thenReturn(Optional.empty());
        assertThrows(RecourseNotExistException.class, () -> tagService.delete(tagId));
    }

    @Test
    void findAll() {
        int pageNo = 0;
        int pageSize = 2;
        List<Tag> tags = new ArrayList<>();
        tags.add(new Tag(1L, "test1"));
        tags.add(new Tag(2L, "test2"));
        List<TagDto> tagDtoList = new ArrayList<>();
        tagDtoList.add(new TagDto(1L, "test1"));
        tagDtoList.add(new TagDto(2L, "test2"));
        Pageable paging = PageRequest.of(pageNo, pageSize);
        Page<Tag> tagPage =new PageImpl<>(tags);
        Mockito.when(tagRepository.findAll(PageRequest.of(pageNo, pageSize))).thenReturn((tagPage));
        List<TagDto> actual = tagService.findAll(pageNo, pageSize);
        assertEquals(tagDtoList, actual);
    }

    @Test
    void update() {
        TagDto tagDto = new TagDto(1L, "updateTag");
        assertThrows(UnsupportedOperationException.class, () -> tagService.update(tagDto));
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateRepository;
import com.epam.esm.dao.PurchaseRepository;
import com.epam.esm.dao.TagRepository;
import com.epam.esm.dao.entity.GiftCertificate;
import com.epam.esm.dao.entity.Tag;
import com.epam.esm.model.CustomPage;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.GiftCertificateUpdateDto;
import com.epam.esm.model.dto.StateCertificate;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.converter.GiftCertificateConverter;
import com.epam.esm.service.converter.TagConverter;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.service.utils.CertificateParameterApiValidator;
import com.epam.esm.service.utils.CertificateSortParameterValidator;
import com.epam.esm.service.utils.GiftCertificateQDsl;
import com.epam.esm.service.utils.SortUtil;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.model.parameters.CustomErrorCode.*;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final GiftCertificateRepository giftCertificateRepository;
    private final TagRepository tagRepository;
    private final PurchaseRepository purchaseRepository;

    @Autowired
    public CertificateServiceImpl(
            GiftCertificateRepository giftCertificateRepository,
            TagRepository tagRepository, PurchaseRepository purchaseRepository) {
        this.giftCertificateRepository = giftCertificateRepository;
        this.tagRepository = tagRepository;
        this.purchaseRepository = purchaseRepository;
    }

    @Override
    @Transactional
    public GiftCertificateDto add(GiftCertificateDto giftCertificate) throws RecourseExistException {
        Optional<GiftCertificate> existGiftCertificate = giftCertificateRepository.findByName_AndState(giftCertificate.getName(), StateCertificate.ACTIVE);
        if (existGiftCertificate.isPresent()) {
            throw new RecourseExistException(CustomErrorCode.RECOURSE_CERTIFICATE_EXIST,
                    "Certificate={" + giftCertificate.toString() + "} exists");
        }
        for (TagDto tagDto : giftCertificate.getListTagDto()
        ) {
            Optional<Tag> tempTag = tagRepository.findByTagName(tagDto.getTagName());
            tagDto.setTagId(tempTag.map(Tag::getTagId).orElseGet(() -> tagRepository.save(TagConverter.convertFrom(tagDto)).getTagId()));
        }
        GiftCertificate newGiftCertificate = giftCertificateRepository.save(GiftCertificateConverter.convertFrom(giftCertificate));
        return GiftCertificateConverter.convertTo(newGiftCertificate);
    }

    @Override
    public GiftCertificateDto update(GiftCertificateDto giftCertificateDto){
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Transactional
    public GiftCertificateDto update(GiftCertificateUpdateDto giftCertificate) throws RecourseNotExistException {
        GiftCertificate existGiftCertificate = giftCertificateRepository.findById(giftCertificate.getId()).orElseThrow(() ->
                new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate with id={" + giftCertificate.getId() + "} doesn't exist"));
        GiftCertificate updateGiftCertificate = giftCertificateRepository.save(merge(giftCertificate,
                GiftCertificateConverter.convertTo(existGiftCertificate)));
        return GiftCertificateConverter.convertTo(updateGiftCertificate);
    }

    @Override
    public GiftCertificateDto findEntityById(long id) throws RecourseNotExistException {
        GiftCertificate giftCertificate = giftCertificateRepository.findById(id).orElseThrow(() ->
                new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate with id={" + id + "} doesn't exist"));
        return GiftCertificateConverter.convertTo(giftCertificate);
    }

    @Override
    @Transactional
    public void delete(long id) throws RecourseNotExistException {
        GiftCertificate giftCertificate = giftCertificateRepository.findById(id).
                orElseThrow(() -> new RecourseNotExistException(RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + " d} doesn't exist"));
        if (purchaseRepository.countPurchaseByGiftCertificateId(id) != 0) {
            giftCertificate.setState(StateCertificate.DELETED);
            giftCertificateRepository.save(giftCertificate);
        } else {
            giftCertificateRepository.delete(giftCertificate);
        }
    }

    @Override
    public List<GiftCertificateDto> findAll(int pageNo, int pageSize) {
        CustomPage customPage = new CustomPage(pageNo, pageSize, giftCertificateRepository.count());
        List<GiftCertificate> listGiftCertificate = giftCertificateRepository
                .findByState(StateCertificate.ACTIVE, PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()));
        return GiftCertificateConverter.convertTo(listGiftCertificate);
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificateListByParams(Map<String, List<String>> groupParameters,
                                                                    int pageNo,
                                                                    int pageSize)
            throws IllegalRequestParameterException, IllegalRequestSortParameterException {
        if (!CertificateParameterApiValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestParameterException(ILLEGAL_REQUEST_PARAMETER, "The request parameters are illegal");
        }
        if (!CertificateSortParameterValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestSortParameterException(ILLEGAL_SORT_PARAMETER, "The sort parameters are illegal");
        }
        BooleanExpression expression = GiftCertificateQDsl.getBooleanExpression(groupParameters);
        CustomPage customPage = new CustomPage(pageNo, pageSize, giftCertificateRepository.count(expression));
        Sort sort = SortUtil.buildSort(groupParameters);
        Pageable pageable = sort.isUnsorted() ? PageRequest.of(customPage.getPageNumber(), customPage.getPageSize()) :
                PageRequest.of(customPage.getPageNumber(), customPage.getPageSize(), sort);
        Page<GiftCertificate> all = giftCertificateRepository.findAll(expression, pageable);
        return GiftCertificateConverter.convertTo(all.getContent());
    }

    private GiftCertificate merge(GiftCertificateUpdateDto update, GiftCertificateDto exist) {
        if (update.getName() != null && !update.getName().equals(exist.getName())) {
            exist.setName(update.getName());
        }
        if (update.getDescription() != null && !update.getDescription().equals(exist.getDescription())) {
            exist.setDescription(update.getDescription());
        }
        if (update.getDuration() != 0 && update.getDuration() != exist.getDuration()) {
            exist.setDuration(update.getDuration());
        }
        if (update.getPrice() != null && !update.getPrice().equals(exist.getPrice())) {
            update.setPrice(exist.getPrice());
        }
        if (update.getListDelTag() != null) {
            for (TagDto tagDto : update.getListDelTag().stream().distinct().collect(Collectors.toList())
            ) {
                List<TagDto> tagDtoList = exist.getListTagDto().stream().filter(p -> !p.getTagName().equalsIgnoreCase(tagDto.getTagName())).collect(Collectors.toList());
                exist.setListTagDto(tagDtoList);
            }
        }
        if (update.getListAddTag() != null) {
            List<TagDto> tagAddList = update.getListAddTag().stream().distinct().collect(Collectors.toList());
            for (TagDto tagDto : tagAddList
            ) {
                Optional<Tag> tempTag = tagRepository.findByTagName(tagDto.getTagName());
                tagDto.setTagId(tempTag.map(Tag::getTagId).orElseGet(() -> tagRepository.save(TagConverter.convertFrom(tagDto)).getTagId()));
            }
            exist.getListTagDto().addAll(tagAddList);
            List<TagDto> newTagList = exist.getListTagDto().stream().distinct().collect(Collectors.toList());
            exist.setListTagDto(newTagList);
        }
        return GiftCertificateConverter.convertFrom(exist);
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.entity.GiftCertificateDto;
import com.epam.esm.model.entity.TagDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.service.utils.CertificateParameterApiValidator;
import com.epam.esm.service.utils.CertificateSortParameterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.model.parameters.CustomErrorCode.*;

@Service
public class CertificateServiceImpl implements CertificateService {
    private final GiftCertificateDao certificateDao;
    private final TagDao tagDao;

    @Autowired
    public CertificateServiceImpl(GiftCertificateDao certificateDao, TagDao tagDao) {
        this.certificateDao = certificateDao;
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public GiftCertificateDto add(GiftCertificateDto giftCertificate) throws RecourseExistException {
        Map<String, String> groupParams = new HashMap<>();
        Optional<GiftCertificateDto> tempGiftCertificate = certificateDao.findByName(giftCertificate.getName());
        if (tempGiftCertificate.isPresent()) {
            throw new RecourseExistException(CustomErrorCode.RECOURSE_CERTIFICATE_EXIST,"Certificate={" + giftCertificate.toString() + "} exists");
        }
        long certificateId = certificateDao.create(giftCertificate).getId();
        for (TagDto tagDto : giftCertificate.getListTag()
        ) {
            Optional<TagDto> tempTag = tagDao.findByName(tagDto.getTagName());
            tagDto.setTagId(tempTag.map(TagDto::getTagId).orElseGet(() -> tagDao.create(tagDto).getTagId()));
        }
        List<TagDto> distinctListTagDto = giftCertificate.getListTag().stream().distinct().collect(Collectors.toList());
        for (TagDto tagDto : distinctListTagDto
        ) {
            certificateDao.addLinkTag(certificateId, tagDto.getTagId());
        }
        return certificateDao.findEntityById(certificateId).get();
    }

    @Override
    @Transactional
    public GiftCertificateDto update(GiftCertificateDto giftCertificate) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(giftCertificate.getId());
        if (!optionalGiftCertificate.isPresent()) {
            throw new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST,"Certificate with id={" + giftCertificate.getId() + "} doesn't exist");
        }
        return certificateDao.update(giftCertificate);
    }

    @Override
    public GiftCertificateDto findEntityById(long id) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST,"Certificate with id={" + id + "} doesn't exist");
        return optionalGiftCertificate.get();
    }

    @Override
    @Transactional
    public boolean delete(long id) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException(RECOURSE_TAG_NOT_EXIST,"Tag with id={" + id + " d} doesn't exist");
        if (optionalGiftCertificate.get().getListTag() != null) {
            certificateDao.deleteLinkTagById(id);
        }
        return certificateDao.delete(id);
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificateListByParams(Map<String, String> groupParameters)
            throws IllegalRequestParameterException, IllegalRequestSortParameterException {
        if (!CertificateParameterApiValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestParameterException(ILLEGAL_REQUEST_PARAMETER,"The request parameters are illegal");
        }
        if (!CertificateSortParameterValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestSortParameterException(ILLEGAL_SORT_PARAMETER,"The sort parameters are illegal");
        }
        return certificateDao.findEntityByParams(groupParameters);
    }
}

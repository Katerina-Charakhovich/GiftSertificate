package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.TagDao;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.TagDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.IllegalRequestParameterException;
import com.epam.esm.service.exeption.IllegalRequestSortParameterException;
import com.epam.esm.service.exeption.RecourseExistException;
import com.epam.esm.service.exeption.RecourseNotExistException;
import com.epam.esm.service.utils.CertificateParameterApiValidator;
import com.epam.esm.service.utils.CertificateSortParameterValidator;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        Optional<GiftCertificateDto> tempGiftCertificate = certificateDao.findByName(giftCertificate.getName());
        if (tempGiftCertificate.isPresent()) {
            throw new RecourseExistException(CustomErrorCode.RECOURSE_CERTIFICATE_EXIST, "Certificate={" + giftCertificate.toString() + "} exists");
        }
        for (TagDto tagDto : giftCertificate.getListTagDto()
        ) {
            Optional<TagDto> tempTag = tagDao.findByName(tagDto.getTagName());
            tagDto.setTagId(tempTag.map(TagDto::getTagId).orElseGet(() -> tagDao.create(tagDto).getTagId()));
        }
        return certificateDao.create(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDto update(GiftCertificateDto giftCertificate) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(giftCertificate.getId());
        if (!optionalGiftCertificate.isPresent()) {
            throw new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate with id={" + giftCertificate.getId() + "} doesn't exist");
        }
        return certificateDao.update(merge(giftCertificate, optionalGiftCertificate.get()));
    }

    @Override
    public GiftCertificateDto findEntityById(long id) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException(RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate with id={" + id + "} doesn't exist");
        return optionalGiftCertificate.get();
    }

    @Override
    @Transactional
    public void delete(long id) throws RecourseNotExistException {
        Optional<GiftCertificateDto> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException(RECOURSE_TAG_NOT_EXIST, "Tag with id={" + id + " d} doesn't exist");
        certificateDao.delete(optionalGiftCertificate.get());
    }

    @Override
    public List<GiftCertificateDto> findAll(int offset, int limit) {
        return certificateDao.findAll(offset, limit);
    }

    @Override
    public List<GiftCertificateDto> findGiftCertificateListByParams(Map<String, List<String>> groupParameters,
                                                                    int offset,
                                                                    int limit)
            throws IllegalRequestParameterException, IllegalRequestSortParameterException {
        if (!CertificateParameterApiValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestParameterException(ILLEGAL_REQUEST_PARAMETER, "The request parameters are illegal");
        }
        if (!CertificateSortParameterValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestSortParameterException(ILLEGAL_SORT_PARAMETER, "The sort parameters are illegal");
        }
        return certificateDao.findEntityByParams(groupParameters, offset, limit);
    }

    private GiftCertificateDto merge(GiftCertificateDto update, GiftCertificateDto exist) {
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
        if (update.getListTagDto() != null) {
            for (TagDto tagDto : update.getListTagDto()
            ) {
                Optional<TagDto> tempTag = tagDao.findByName(tagDto.getTagName());
                tagDto.setTagId(tempTag.map(TagDto::getTagId).orElseGet(() -> tagDao.create(tagDto).getTagId()));
            }
            exist.setListTagDto(update.getListTagDto());
        }
        exist.setListTagDto(update.getListTagDto());
        return exist;
    }
}

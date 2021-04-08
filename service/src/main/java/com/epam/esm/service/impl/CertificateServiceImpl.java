package com.epam.esm.service.impl;

import com.epam.esm.dao.dao.GiftCertificateDao;
import com.epam.esm.model.parameters.CertificateParameter;
import com.epam.esm.dao.dao.TagDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.model.entity.Tag;
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
    public GiftCertificate add(GiftCertificate giftCertificate) throws RecourseExistException {
        Map<String, String> groupParams = new HashMap<>();

        Optional<GiftCertificate> tempGiftCertificate= certificateDao.findByName(giftCertificate.getName());
        if (tempGiftCertificate.isPresent()) {
            throw new RecourseExistException("Certificate={" + giftCertificate.toString() + "} exists");
        }
        long certificateId = certificateDao.create(giftCertificate).getId();
        for (Tag tag : giftCertificate.getListTag()
        ) {
            Optional<Tag> tempTag = tagDao.findByName(tag.getTagName());
            tag.setTagId(tempTag.map(Tag::getTagId).orElseGet(() -> tagDao.create(tag).getTagId()));
        }
        List<Tag> distinctListTag = giftCertificate.getListTag().stream().distinct().collect(Collectors.toList());
        for (Tag tag : distinctListTag
        ) {
            certificateDao.addLinkTag(certificateId, tag.getTagId());
        }
        return certificateDao.findEntityById(certificateId).get();

    }

    @Override
    @Transactional
    public GiftCertificate update(GiftCertificate giftCertificate) throws RecourseNotExistException {
        Optional<GiftCertificate> optionalGiftCertificate = certificateDao.findEntityById(giftCertificate.getId());
        if (!optionalGiftCertificate.isPresent()) {
            throw new RecourseNotExistException("Certificate with id={" + giftCertificate.getId() + "} doesn't exist");
        }
        return certificateDao.update(giftCertificate);
    }



    @Override
    public GiftCertificate findEntityById(long id) throws RecourseNotExistException {
        Optional<GiftCertificate> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException("Certificate with id={" + id + "} doesn't exist");
        return optionalGiftCertificate.get();
    }

    @Override
    @Transactional
    public boolean delete(long id) throws RecourseNotExistException {
        Optional<GiftCertificate> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        if (optionalGiftCertificate.get().getListTag() != null) {
           certificateDao.deleteLinkTagById(id);
        }
        return certificateDao.delete(id);
    }

    @Override
    public List<GiftCertificate> findGiftCertificateListByParams(Map<String, String> groupParameters)
            throws IllegalRequestParameterException, IllegalRequestSortParameterException {
        if (!CertificateParameterApiValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestParameterException("The request parameters are illegal");
        }
        if (!CertificateSortParameterValidator.isValidParams(groupParameters)) {
            throw new IllegalRequestSortParameterException("The sort parameters are illegal");
        }
        return certificateDao.findEntityByParams(groupParameters);
    }
}

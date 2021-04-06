package com.epam.esm.service.impl;

import com.epam.esm.dao.dao.GiftCertificateDao;
import com.epam.esm.model.entity.GiftCertificate;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class CertificateServiceImpl implements CertificateService {
    private final GiftCertificateDao certificateDao;

    @Autowired
    public CertificateServiceImpl(GiftCertificateDao certificateDao) {
        this.certificateDao = certificateDao;
    }

    @Override
    public GiftCertificate add(GiftCertificate giftCertificate)  {
        return null;
    }

    @Override
    public GiftCertificate update(GiftCertificate giftCertificate) {
        return null;
    }

    @Override
    public GiftCertificate findEntityById(long id) throws RecourseNotExistException {
        Optional<GiftCertificate> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (optionalGiftCertificate.isEmpty())
            throw new RecourseNotExistException("Certificate with id={" + id + "} doesn't exist");
        return optionalGiftCertificate.get();
    }

    @Override
    @Transactional
    public boolean delete(long id) throws RecourseNotExistException {
        Optional<GiftCertificate> optionalGiftCertificate = certificateDao.findEntityById(id);
        if (!optionalGiftCertificate.isPresent())
            throw new RecourseNotExistException("Tag with id={" + id + " d} doesn't exist");
        if (optionalGiftCertificate.get().getListTag()!=null){
          boolean s= certificateDao.deleteLinkTagById(id);
        }
        return certificateDao.delete(id);
    }
}

package com.epam.esm.service.impl;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.PurchaseDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.model.dto.GiftCertificateDto;
import com.epam.esm.model.dto.PurchaseShortDto;
import com.epam.esm.model.dto.PurchaseDto;
import com.epam.esm.model.dto.UserDto;
import com.epam.esm.model.parameters.CustomErrorCode;
import com.epam.esm.service.PurchaseService;
import com.epam.esm.service.exeption.RecourseNotExistException;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.epam.esm.model.parameters.CustomErrorCode.RECOURSE_PURCHASE_NOT_EXIST;

@AllArgsConstructor
@Service
public class PurchaseServiceImpl implements PurchaseService {
    @Autowired
    private final UserDao userDao;
    @Autowired
    private final PurchaseDao purchaseDao;
    @Autowired
    private final GiftCertificateDao giftCertificateDao;

    @Override
    public PurchaseDto add(PurchaseDto purchaseDto) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PurchaseDto update(PurchaseDto purchaseDto) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public PurchaseDto findEntityById(long id) throws RecourseNotExistException {
        Optional<PurchaseDto> optionalPurchaseDto = purchaseDao.findEntityById(id);
        if (!optionalPurchaseDto.isPresent())
            throw new RecourseNotExistException(RECOURSE_PURCHASE_NOT_EXIST, "Purchase with id={" + id + "} doesn't exist");
        return optionalPurchaseDto.get();
    }

    @Override
    public Executable delete(long id) throws RecourseNotExistException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<PurchaseDto> findAll(int offset, int limit) {
        return purchaseDao.findAll(offset, limit);
    }

    @Override
    @Transactional
    public PurchaseDto addPurchase(PurchaseShortDto purchaseCreateDto) throws RecourseNotExistException {
        Optional<UserDto> userDtoOptional = userDao.findEntityById(purchaseCreateDto.getUserId());
        if (!userDtoOptional.isPresent()) {
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_NOT_EXIST, "User={"
                    + purchaseCreateDto.getUserId() + "} doesn't exist");
        }
        for (long giftCertificateId : purchaseCreateDto.getListGiftCertificate()
        ) {
            Optional<GiftCertificateDto> optionalGiftCertificateDto = giftCertificateDao
                    .findEntityById(giftCertificateId);
            if (!optionalGiftCertificateDto.isPresent()) {
                throw new RecourseNotExistException(CustomErrorCode.RECOURSE_CERTIFICATE_NOT_EXIST, "Certificate={"
                        + giftCertificateId + "} doesn't exist");
            }
        }
        return findEntityById(purchaseDao.addPurchaseDto(purchaseCreateDto).getId());
    }

    @Override
    public List<PurchaseDto> findListPurchaseByUserId(long userId) throws RecourseNotExistException {
        Optional<UserDto> userDtoOptional = userDao.findEntityById(userId);
        if (!userDtoOptional.isPresent()) {
            throw new RecourseNotExistException(CustomErrorCode.RECOURSE_USER_NOT_EXIST, "User={"
                    + userId + "} doesn't exist");
        }
        return purchaseDao.findByUserId(userId);
    }
}

package com.epam.esm.service.utils;

import com.epam.esm.dao.entity.QGiftCertificate;
import com.epam.esm.model.dto.StateCertificate;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GiftCertificateQDsl {
    private static final String TAG_NAME = "tagName";
    private static final String CERTIFICATE_NAME = "name";
    private static final String CERTIFICATE_DESCRIPTION = "description";
    private static final String SIGN = "%";
    private static final String PARAMETER_SORT = "sort";

    private GiftCertificateQDsl() {
    }

    /**
     * Method returns BooleanExpression (QueryDsl predicate), according to input parameters
     *
     * @param groupParameters group of parameters
     * @return the boolean expression
     */
    public static BooleanExpression getBooleanExpression(Map<String, List<String>> groupParameters) {
        Map<String, List<String>> findParams = groupParameters.entrySet().stream().filter(s -> !s.getKey().equalsIgnoreCase(PARAMETER_SORT))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        QGiftCertificate certificate = QGiftCertificate.giftCertificate;
        BooleanExpression expression = certificate.isNotNull();
        expression.and(certificate.state.eq(StateCertificate.ACTIVE));
        for (Map.Entry<String, List<String>> param : findParams.entrySet()
        ) {
            switch (param.getKey()) {
                case TAG_NAME:
                    for (String tagName : param.getValue()
                    ) {
                        expression = expression.and(certificate.listTag.any().tagName.like(tagName));
                    }
                    break;
                case CERTIFICATE_NAME:
                    for (String certificateName : param.getValue()
                    ) {
                        expression = expression.and(certificate.name.like(SIGN + certificateName + SIGN));
                    }
                    break;
                case CERTIFICATE_DESCRIPTION:
                    for (String certificateDesc : param.getValue()
                    ) {
                        expression = expression.and(certificate.description.like(SIGN + certificateDesc + SIGN));
                    }
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: ");
            }
        }
        return expression;
    }
}

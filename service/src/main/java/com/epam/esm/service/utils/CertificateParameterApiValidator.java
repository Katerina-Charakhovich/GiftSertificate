package com.epam.esm.service.utils;

import com.epam.esm.model.parameters.CertificateParameter;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CertificateParameterApiValidator {
    private static final String NAME_PATTERN = "[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]{3,45}";
    private static final String DESCRIPTION_PATTERN =  "[a-zA-Zа-яА-ЯёЁ0-9\\s?!,.:'\\-]{1,1000}";
    private static final String PARAMETER_SORT = "sort";

    public static boolean isValidParams(Map<String, String> groupParameters) {
        Map<String, String> groupParams = groupParameters.entrySet().stream().
                filter(s -> !s.getKey().toUpperCase().equals(PARAMETER_SORT.toUpperCase())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return groupParams.entrySet().stream().allMatch(CertificateParameterApiValidator::isValidParameterNameValue);

    }

    public static boolean isValidParameterNameValue(Map.Entry<String, String> param) {
        boolean result = false;
        Optional<CertificateParameter> apiParam = Arrays
                .stream(CertificateParameter.values())
                .filter(s -> s.getName().equals(param.getKey())).findFirst();
        if (apiParam.isPresent()) {
            switch (apiParam.get()) {
                case TAG_NAME:
                    result = Pattern.matches(NAME_PATTERN, param.getValue());
                    break;
                case CERTIFICATE_NAME:
                    result = Pattern.matches(NAME_PATTERN, param.getValue());
                case CERTIFICATE_DESCRIPTION:
                    result = Pattern.matches(DESCRIPTION_PATTERN, param.getValue());
                    break;
                case CREATE_DATE:
                    break;
            }
        }
        return result;
    }
}

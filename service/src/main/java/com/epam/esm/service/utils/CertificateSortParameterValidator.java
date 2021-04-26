package com.epam.esm.service.utils;

import com.epam.esm.model.parameters.CertificateSortType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CertificateSortParameterValidator {
    private static final String PARAMETER_SORT = "sort";
    private static final char MINUS = '-';

    public static boolean isValidParams(Map<String, List<String>> groupParameters) {
        return groupParameters.containsKey(PARAMETER_SORT) ? isValidParameterValue(groupParameters.get(PARAMETER_SORT)) : true;
    }

    public static boolean isValidParameterValue(List<String> value) {
        return value.stream().map(s -> s.charAt(0) == MINUS && !(s.length() <= 1) ? s.substring(1) : s)
                .allMatch(s -> isSortTypeValid(s));
    }

    public static boolean isSortTypeValid(String value) {
        return Arrays.stream(CertificateSortType.values())
                .filter(s -> s.getType().getParamName().equals(value))
                .findFirst().isPresent();
    }
}

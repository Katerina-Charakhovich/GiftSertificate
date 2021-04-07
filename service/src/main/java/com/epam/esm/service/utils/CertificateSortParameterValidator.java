package com.epam.esm.service.utils;

import com.epam.esm.model.parameters.CertificateSortType;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class CertificateSortParameterValidator {
    private static final String PARAMETER_SORT = "sort";
    private static final char MINUS = '-';

    public static boolean isValidParams(Map<String, String> groupParameters) {
        Map<String, String> groupSortParameters = groupParameters.entrySet().stream().
                filter(s -> s.getKey().toUpperCase().equals(PARAMETER_SORT.toUpperCase())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        boolean b= groupSortParameters.entrySet().stream().allMatch(s -> isValidParameterValue(s.getValue()));
        return groupSortParameters.entrySet().stream().anyMatch(s -> isValidParameterValue(s.getValue()));
    }

    public static boolean isValidParameterValue(String value) {
        String tempValue = (value.charAt(0) == MINUS && value.length()>1)?value.substring(1):value;
        Optional<CertificateSortType> sortType=Arrays.stream(CertificateSortType.values())
                .filter(s -> s.getType().getParamName().toUpperCase().equals(tempValue.toUpperCase()))
                        .findFirst();
        return sortType.isPresent();
    }
}

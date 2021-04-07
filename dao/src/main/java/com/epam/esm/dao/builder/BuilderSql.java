package com.epam.esm.dao.builder;

import com.epam.esm.model.parameters.CertificateApiParameter;
import com.epam.esm.model.parameters.CertificateSortType;

import java.util.Map;
import java.util.stream.Collectors;

public class BuilderSql {
    public static final String PERCENT_SIGN = "%";
    public static final String ORDER = "ORDER BY";
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String LIKE_OPERAND = "like";
    private static final String PARAMETER_SORT = "sort";
    public static final String EQUALS = "=";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    private static final char MINUS = '-';
    private static final String AND = "AND";
    private static final String QUOTES="\"";
    private static final String WHERE="where";

    public static String buildFindCertificateByAllParameters(Map<String, String> groupParameters) {
        StringBuilder result = new StringBuilder();
        Map<String, String> groupParams = groupParameters.entrySet().stream().
                filter(s -> !s.getKey().toUpperCase().equals(PARAMETER_SORT.toUpperCase())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        Map<String, String> groupSortParams = groupParameters.entrySet().stream().
                filter(s -> s.getKey().toUpperCase().equals(PARAMETER_SORT.toUpperCase())).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return result.
                append(buildFindCertificateByParameters(groupParams)).
                append(SPACE).
                append(buildSortCertificateByParameters(groupSortParams)).toString();
    }

    public static String buildFindCertificateByParameters(Map<String, String> groupParameters) {
        int size = groupParameters.size();
        if (size == 0) {
            return EMPTY;
        }
        StringBuilder resultSql = new StringBuilder(SPACE).append(WHERE).append(SPACE);
        int i = 1;
        for (Map.Entry<String, String> entry : groupParameters.entrySet()
        ) {
            resultSql.
                    append(CertificateApiParameter.getByName(entry.getKey()).get().getParamValue()).
                    append(SPACE).
                    append(LIKE_OPERAND).
                    append(SPACE).
                    append(QUOTES).
                    append(PERCENT_SIGN).
                    append(entry.getValue()).
                    append(PERCENT_SIGN).
                    append(QUOTES);
            if (i != size) {
                resultSql.append(SPACE).
                        append(AND).append(SPACE);
            }
            i++;
        }
        return resultSql.toString();
    }

    public static String buildSortCertificateByParameters(Map<String, String> groupParameters) {
        if (groupParameters.size() == 0) {
            return EMPTY;
        }
        StringBuilder resultSql = new StringBuilder(ORDER).append(SPACE);
        for (Map.Entry<String, String> entry : groupParameters.entrySet()
        ) {
            String tempValue = entry.getValue();
            String typeSort = ASC;
            if (tempValue.charAt(0) == MINUS) {
                tempValue = tempValue.substring(1);
                typeSort = DESC;
            }
            resultSql.append(CertificateSortType.getByType(tempValue).get().getType().getParamValue()).
                    append(SPACE).
                    append(typeSort).
                    append(COMMA);
        }
        return resultSql.delete(resultSql.length() - 1, resultSql.length()).toString();
    }

}

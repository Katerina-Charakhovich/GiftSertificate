package com.epam.esm.dao.builder;

import com.epam.esm.model.parameters.CertificateParameter;
import com.epam.esm.model.parameters.CertificateSortType;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BuilderSql {
    public static final String PERCENT_SIGN = "%";
    public static final String ORDER = "ORDER BY";
    public static final String ASC = "ASC";
    public static final String DESC = "DESC";
    public static final String LIKE_OPERAND = "like ";
    public static final String SINGLE_QUOTE = "\'";
    private static final String PARAMETER_SORT = "sort";
    public static final String COMMA = ",";
    public static final String SPACE = " ";
    public static final String EMPTY = "";
    private static final char MINUS = '-';
    private static final String AND = "AND";
    private static final String QUOTES = "\"";
    private static final String WHERE = "where";
    private static final String POINT = ".";

    public static String buildFindAndSortCertificateByParameter(Map<String, List<String>> groupParameters) {
        StringBuilder result = new StringBuilder();
        Map<String, List<String>> groupParams = groupParameters.entrySet().stream().
                filter(s -> !s.getKey().equalsIgnoreCase(PARAMETER_SORT)).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        List<String> sort = groupParameters.get(PARAMETER_SORT);
        return result.
                append(buildFindCertificateByParameter(groupParams)).
                append(SPACE).
                append(buildSortCertificateByParameters(sort)).toString();
    }

    public static String buildFindCertificateByParameter(Map<String, List<String>> groupParameters) {
        int size = groupParameters.size();
        if (size == 0) {
            return EMPTY;
        }
        StringBuilder resultSql = new StringBuilder(SPACE).append(WHERE).append(SPACE);
        int i = 1;

        for (Map.Entry<String, List<String>> entry : groupParameters.entrySet()
        ) {
            for (String eachValue : entry.getValue()
            ) {
                resultSql.
                        append(CertificateParameter.getByName(entry.getKey()).get().getEntityName()).
                        append(POINT).append(CertificateParameter.getByName(entry.getKey()).get().getParamName()).
                        append(SPACE).
                        append(LIKE_OPERAND).
                        append(SINGLE_QUOTE).
                        append(PERCENT_SIGN).
                        append(eachValue).
                        append(PERCENT_SIGN).
                        append(SINGLE_QUOTE).
                        append(SPACE).append(AND).append(SPACE);
            }
        }
        return resultSql.substring(0, resultSql.length() -(AND+SPACE).length());
    }

    public static String buildSortCertificateByParameters(List<String> params) {
        if (params == null) {
            return EMPTY;
        }
        if (params.size() == 0) {
            return EMPTY;
        }
        StringBuilder resultSql = new StringBuilder(ORDER).append(SPACE).append(params.stream()
                .map(s -> (s.charAt(0) == MINUS ?
                        CertificateSortType.getByType(s.substring(1)).get().getType().getEntityName() + POINT +
                                CertificateSortType.getByType(s.substring(1)).get().getType().getParamName() + SPACE + DESC :
                        CertificateSortType.getByType(s).get().getType().getEntityName() + POINT +
                                CertificateSortType.getByType(s).get().getType().getParamName() + SPACE + ASC)).
                        collect(Collectors.joining(COMMA)));
        return resultSql.toString();
    }
}

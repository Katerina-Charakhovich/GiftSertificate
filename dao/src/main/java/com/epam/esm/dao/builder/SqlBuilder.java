package com.epam.esm.dao.builder;

import java.util.Map;

public class SqlBuilder {
    public static final String EQUALS="=";
    public static final String WHERE="WHERE";
    public static final String COMMA=",";
    public static final String SPACE=" ";
    public static String buildCertificateSql(String startSql, Map<String,String> groupParam){
        StringBuilder resultSql=new StringBuilder(startSql).append(SPACE).append(WHERE).append(SPACE);
        for (Map.Entry<String,String> entry:groupParam.entrySet()
             ) {
            resultSql.append(entry.getKey()).append(EQUALS).append(entry.getValue()).append(COMMA);
        }
        return resultSql.delete(resultSql.length()-1,resultSql.length()-1).toString();
    }
}

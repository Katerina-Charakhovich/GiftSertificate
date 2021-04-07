package com.epam.esm.service.builder;

import com.epam.esm.dao.builder.BuilderSql;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class BuilderSqlTest {

    @Test
    void buildFindCertificateByAllParameters() {
        Map<String,String> groupParams= new HashMap<>();
        groupParams.put("name","certificate");
        groupParams.put("description","certificate");
        groupParams.put("sort","-name");
        String str= BuilderSql.buildFindCertificateByAllParameters(groupParams);
        int i=0;
    }
}
package com.epam.esm.service.utils;

import com.querydsl.core.types.dsl.BooleanExpression;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateQDslTest {

    @Test
    void getBooleanExpression() {
        Map<String, List<String>> groupParameters=new HashMap<>();
        List<String> value=new ArrayList<>();
        value.add("test");
        groupParameters.put("test",value);
        assertThrows(IllegalStateException.class, () -> GiftCertificateQDsl.getBooleanExpression(groupParameters));
    }
}
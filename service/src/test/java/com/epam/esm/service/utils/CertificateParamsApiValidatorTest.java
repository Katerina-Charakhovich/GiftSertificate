package com.epam.esm.service.utils;

import com.epam.esm.model.parameters.CertificateParameter;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CertificateParamsApiValidatorTest {

    @Test
    void isGroupParametersValidPositive() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateParameter.CERTIFICATE_DESCRIPTION.getName(),"test");
        assertTrue( CertificateParameterApiValidator.isValidParams(param));
    }
    @Test
    void isGroupParametersValidNegative() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateParameter.CERTIFICATE_DESCRIPTION.getName(),"test");
        param.putIfAbsent("test","test");
        assertFalse( CertificateParameterApiValidator.isValidParams(param));
    }


    @Test
    void isValidParameterValue() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateParameter.CERTIFICATE_DESCRIPTION.getName(),"Так в том то и дело, что все на словах. Это и плохо");
        assertTrue( CertificateParameterApiValidator.isValidParams(param));
    }
}

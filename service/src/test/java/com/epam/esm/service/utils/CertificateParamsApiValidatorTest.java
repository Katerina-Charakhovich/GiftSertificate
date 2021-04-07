package com.epam.esm.service.utils;

import com.epam.esm.model.parameters.CertificateApiParameter;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class CertificateParamsApiValidatorTest {

    @Test
    void isGroupParametersValidPositive() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateApiParameter.CERTIFICATE_DESCRIPTION.getParamName(),"test");
        Assert.assertTrue( CertificateParameterApiValidator.isValidParams(param));
    }
    @Test
    void isGroupParametersValidNegative() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateApiParameter.CERTIFICATE_DESCRIPTION.getParamName(),"test");
        param.putIfAbsent("test","test");
        Assert.assertFalse( CertificateParameterApiValidator.isValidParams(param));
    }


    @Test
    void isValidParameterValue() {
        Map<String,String> param=new HashMap<>();
        param.putIfAbsent(CertificateApiParameter.CERTIFICATE_DESCRIPTION.getParamName(),"Так в том то и дело, что все на словах. Это и плохо");
        Assert.assertTrue( CertificateParameterApiValidator.isValidParams(param));
    }
}

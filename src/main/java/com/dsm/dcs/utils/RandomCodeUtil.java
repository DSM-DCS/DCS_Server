package com.dsm.dcs.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * RandomCodeUtil with length
     *
     * @return Random String & Integer code
     **/
    public static String generateRandomCode(Integer codeLength) {
        return RandomStringUtils.randomAlphanumeric(codeLength);
    }

    /**
     * RandomCodeUtil with length
     *
     * @return Random Integer code
     **/
    public static String generateRandomNumber(Integer codeLength) {
        return RandomStringUtils.randomNumeric(codeLength);

    }
}


package com.dsm.dcs.utils;

import net.bytebuddy.utility.RandomString;

public class RandomCodeUtil {

    private RandomCodeUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String make(Integer codeLength) {
        return RandomString.make(codeLength);
    }
}

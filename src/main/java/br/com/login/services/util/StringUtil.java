package br.com.login.services.util;

import br.com.login.exceptions.StringUtilException;

public class StringUtil {
    private StringUtil(){}

    public static boolean checkStringIsValid(String value) throws StringUtilException {
        if(value == null || value.isBlank())
            throw new StringUtilException();
        return true;
    }

    public static void checkStringLength(String value, Long length) throws StringUtilException {
        if (!checkStringIsValid(value) || value.length() < length)
            throw new StringUtilException();
    }


}

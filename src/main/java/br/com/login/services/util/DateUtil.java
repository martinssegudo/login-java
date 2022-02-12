package br.com.login.services.util;

import br.com.login.exceptions.CreateUserException;
import br.com.login.exceptions.DateUtilException;

import java.time.LocalDate;

public class DateUtil {
    private DateUtil(){}

    public static void checkLocalDateNotNull(LocalDate date) throws DateUtilException {
        if(date == null)
            throw new DateUtilException();
    }
}

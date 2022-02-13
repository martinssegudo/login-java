package br.com.login.services.util;

import br.com.login.exceptions.DateUtilException;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateUtil {
    private DateUtil(){}

    public static void checkLocalDateNotNull(LocalDate date) throws DateUtilException {
        if(date == null)
            throw new DateUtilException();
    }

    public static void checkLocalDateTimeNotNull(LocalDateTime dateTime) throws DateUtilException {
        if(dateTime == null)
            throw new DateUtilException();
    }
}

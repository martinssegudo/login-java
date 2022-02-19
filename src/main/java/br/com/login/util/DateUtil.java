package br.com.login.util;

import br.com.login.exceptions.DateUtilException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    public static LocalDate convertStgringToLocalDate(String date, String format) throws DateUtilException {
        if(date == null || date.isBlank())
            throw new DateUtilException();
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format));
    }

}

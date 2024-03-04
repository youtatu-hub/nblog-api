package cn.net.perfect.storage.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static final String YEAR_MONTH_DAY_TIME_00_00_00_FORMAT = "yyyy-MM-dd 00:00:00";

    public static Date formatDateTime(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String dateStr = sdf.format(date);
        try {
            Date newDate = sdf.parse(dateStr);
            return newDate;
        } catch (ParseException e) {
            log.error("formatDateTime input:{}, format:{} e:{}", date, format, e);
            return null;
        }
    }

}

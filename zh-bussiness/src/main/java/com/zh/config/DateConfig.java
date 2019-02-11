package com.zh.config;

import com.zh.exceptions.UnifiedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;

import javax.annotation.Nonnull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author  hahaha
 * @since 2018-09-14 15:55

 **/
@Configuration
@Slf4j
public class DateConfig {
    /***
     * Date日期类型转换器
     */
    @Bean
    public Formatter<Date> dateFormatter() {
        return new Formatter<Date>() {
            @Override
            public @Nonnull
            Date parse(@Nonnull String text, @Nonnull Locale locale) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date date;
                try {
                    date = sdf.parse(text);
                } catch (Exception e) {
                    throw new UnifiedException("日期不符合格式");
                }
                return date;
            }

            @Override
            public @Nonnull
            String print(@Nonnull Date object, @Nonnull Locale locale) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                return sdf.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDate> localDateFormatter() {
        return new Formatter<LocalDate>() {
            @Override
            public @Nonnull
            LocalDate parse(@Nonnull String text, @Nonnull Locale locale) {
                return LocalDate.parse(text, DateTimeFormatter.ISO_LOCAL_DATE);
            }

            @Override
            public @Nonnull
            String print(@Nonnull LocalDate object, @Nonnull Locale locale) {
                return DateTimeFormatter.ISO_LOCAL_DATE.format(object);
            }
        };
    }

    @Bean
    public Formatter<LocalDateTime> localDateTimeFormatter() {
        return new Formatter<LocalDateTime>() {
            @Override
            public @Nonnull
            String print(@Nonnull LocalDateTime localDateTime, @Nonnull Locale locale) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                return formatter.format(localDateTime);
            }

            @Override
            public @Nonnull
            LocalDateTime parse(@Nonnull String text, @Nonnull Locale locale) {
                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            }
        };
    }

}

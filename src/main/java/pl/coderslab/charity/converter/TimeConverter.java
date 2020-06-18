package pl.coderslab.charity.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class TimeConverter implements Converter<String, LocalTime> {
    @Override
    public LocalTime convert(String s) {
        return LocalTime.parse(s);
    }
}

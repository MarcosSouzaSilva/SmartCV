package com.smartcv.smartcv.dto.converter;

import com.smartcv.smartcv.dto.Profession;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessionConverter implements Converter<String, Profession> {
    @Override
    public Profession convert(String source) {
        return Profession.valueOf(source.replace(" ", "_").toUpperCase());
    }
}

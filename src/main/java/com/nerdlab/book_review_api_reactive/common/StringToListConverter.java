package com.nerdlab.book_review_api_reactive.common;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@ReadingConverter
public class StringToListConverter implements Converter<String, List<String>> {

    @Override
    public List<String> convert(String source) {
        if (source == null || source.isBlank()) {
            return Collections.emptyList();
        }
        return Arrays.asList(source.split("\\|"));
    }
}

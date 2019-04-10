package com.nex3z.spring.examples.webflux.simple.formatter;

import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class MoneyWriteConverter implements Converter<Money, Long> {

    @Override
    public Long convert(Money source) {
        return source.getAmountMinorLong();
    }

}

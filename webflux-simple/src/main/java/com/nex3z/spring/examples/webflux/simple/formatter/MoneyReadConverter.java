package com.nex3z.spring.examples.webflux.simple.formatter;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class MoneyReadConverter implements Converter<Long, Money> {

    @Override
    public Money convert(Long source) {
        return Money.ofMinor(CurrencyUnit.USD, source);
    }

}

package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class LongConverter extends Conversion<Long, String> {

    @Override
    public Long convertOne(String value) {
        return Long.parseLong(value);
    }

    @Override
    public String convertTwo(Long value) {
        if (value == null) return null;
        return value.toString();
    }
}

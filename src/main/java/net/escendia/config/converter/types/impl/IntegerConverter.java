package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class IntegerConverter extends Conversion<Integer, String> {
    @Override
    public Integer convertOne(String value) {
        return Integer.parseInt(value);
    }

    @Override
    public String convertTwo(Integer value) {
        if (value == null) return null;
        return value.toString();
    }
}

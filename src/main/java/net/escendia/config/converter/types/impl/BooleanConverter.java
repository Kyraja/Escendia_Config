package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class BooleanConverter extends Conversion<Boolean, String> {
    @Override
    public Boolean convertOne(String value) {
        return Boolean.parseBoolean(value);
    }

    @Override
    public String convertTwo(Boolean value) {
        if (value == null) return null;
        return value.toString();
    }

}

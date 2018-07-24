package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class FloatConverter extends Conversion<Float, String> {
    @Override
    public Float convertOne(String value) {
        return Float.parseFloat(value);
    }

    @Override
    public String convertTwo(Float value) {
        if (value == null) return null;
        return value.toString();
    }
}

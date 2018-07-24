package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class DoubleConverter extends Conversion<Double, String> {
    @Override
    public Double convertOne(String value) {
        return Double.parseDouble(value);
    }

    @Override
    public String convertTwo(Double value) {
        if (value == null) return null;
        return value.toString();
    }
}

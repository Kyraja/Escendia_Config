package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

import java.math.BigInteger;

public class BigIntegerConverter extends Conversion<BigInteger, String> {
    @Override
    public BigInteger convertOne(String value) {
        return new BigInteger(value);
    }

    @Override
    public String convertTwo(BigInteger value) {
        if (value == null) return null;
        return value.toString();
    }
}

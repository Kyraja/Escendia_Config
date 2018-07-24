package net.escendia.config.converter.types;

import net.escendia.config.converter.Conversion;

public class BaseConverter<S, T> extends Conversion<S, T> {

    @Override
    public S convertOne(T value) {
        return (S) value;
    }

    @Override
    public T convertTwo(S value) {
        return (T) value;
    }
}

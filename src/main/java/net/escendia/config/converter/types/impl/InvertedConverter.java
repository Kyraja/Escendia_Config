package net.escendia.config.converter.types.impl;

import net.escendia.config.converter.Conversion;

public class InvertedConverter<S, T> extends Conversion<S, T> {

    Conversion parent;

    public InvertedConverter(Conversion parent) {
        super(false);
        this.parent = parent;
        this.one = parent.getTwo();
        this.two = parent.getOne();
    }

    @Override
    public S convertOne(T value) {
        return (S) parent.convertTwo(value);
    }

    @Override
    public T convertTwo(S value) {
        return (T) parent.convertOne(value);
    }
}

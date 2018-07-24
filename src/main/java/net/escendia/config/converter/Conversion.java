package net.escendia.config.converter;

import java.lang.reflect.ParameterizedType;

public abstract class Conversion<S, T> {

    protected Class<?> one;
    protected Class<?> two;

    public Conversion() {
        this(true);
    }

    public Conversion(boolean init) {
        if (init) {
            one = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
            two = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        }
    }

    public abstract S convertOne(T value);

    public abstract T convertTwo(S value);

    public T convert(S value) {
        return convertTwo(value);
    }

    public Class<?> getOne() {
        return one;
    }

    public Class<?> getTwo() {
        return two;
    }

    public Conversion getInverse() {
        return TypeConverter.getConverter(two, one);
    }
}

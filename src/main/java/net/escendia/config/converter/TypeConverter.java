package net.escendia.config.converter;

import net.escendia.config.converter.types.BaseConverter;
import net.escendia.config.converter.types.impl.*;

public class TypeConverter {
    private static TypeConverter ourInstance = new TypeConverter();
    private static Conversion[] converters = new Conversion[0];

    private TypeConverter() {
        System.out.println("Init TypeConverter");

    }

    public static void registerConverterList(){
        TypeConverter.registerConverter(new BigIntegerConverter());
        TypeConverter.registerConverter(new BooleanConverter());
        TypeConverter.registerConverter(new DoubleConverter());
        TypeConverter.registerConverter(new FloatConverter());
        TypeConverter.registerConverter(new IntegerConverter());
        TypeConverter.registerConverter(new LongConverter());
        TypeConverter.registerConverter(new ListConverter());
        TypeConverter.registerConverter(new MapConverter());
    }

    public static TypeConverter getInstance() {
        registerConverterList();
        return ourInstance;
    }

    public static <T, S> T convertTo(S obj, Class<? extends T> to) {
        if (obj == null) return (T) null;
        Class<?> from = obj.getClass();
        if (to.isAssignableFrom(from)) return (T) obj;
        Conversion c = getConverter(from, to);
        if (c != null) return (T) c.convertTwo(obj);
        return null;
    }

    public static <T, S> T convertTo(S obj, Conversion<S, T> con) {
        if (obj == null) return (T) null;
        return con.convertTwo(obj);
    }

    public static <S, T> Conversion<S, T> getConverter(Class<? extends S> from, Class<? extends T>... to) {
        if(converters.length==0)registerConverterList();
        for (Class<?> x : to) if (x.isAssignableFrom(from)) return new BaseConverter<S, T>();
        for (Conversion c : converters) {
            if (c.getOne() == from) {
                for (Class<?> x : to) {
                    if (c.getTwo() == x) {
                        //Found a converter
                        return c;
                    }
                }
            }
            if (c.getOne().isAssignableFrom(from)) {
                for (Class<?> x : to) {
                    if (x.isAssignableFrom(c.getTwo())) {
                        //Found a converter
                        return c;
                    }
                }
            }
        }
        return null;
    }

    public static void registerConverter(Conversion converter) {
        Conversion[] n = new Conversion[converters.length + 2];
        for (int i = 0; i < converters.length; i++) n[i] = converters[i];
        n[n.length - 2] = converter;
        n[n.length - 1] = new InvertedConverter(converter);
        converters = n;
    }
}

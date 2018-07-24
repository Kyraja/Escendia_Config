package net.escendia.config.converter.types.impl;

import com.google.common.base.Splitter;
import com.google.gson.Gson;
import net.escendia.config.converter.Conversion;
import net.escendia.config.converter.TypeConverter;
import ninja.leaping.configurate.objectmapping.ObjectMapper;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MapConverter extends Conversion<Map, String> {

    @Override
    public Map convertOne(String value) {
        return new Gson().fromJson(value, Map.class);
    }

    @Override
    public String convertTwo(Map value) {
        return new Gson().toJson(value);
    }
}

package net.escendia.config.converter.types.impl;

import com.google.gson.Gson;
import net.escendia.config.converter.Conversion;

import java.util.Arrays;
import java.util.List;

public class ListConverter extends Conversion<List, String> {

    @Override
    public List convertOne(String value) {

        return new Gson().fromJson(value, List.class);
    }

    @Override
    public String convertTwo(List value) {
        return new Gson().toJson(value);
    }
}

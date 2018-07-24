package net.escendia.config.configs;


import net.escendia.config.annotation.Config;
import net.escendia.config.annotation.ConfigValue;
import net.escendia.config.converter.TypeConverter;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {
    private static ConfigManager ourInstance = new ConfigManager();

    private ConfigManager() {
    }

    public static ConfigManager getInstance() {
        return ourInstance;
    }

    public static <T> T readConfig(T obj) {
        if (obj.getClass().isAnnotationPresent(Config.class)) {
            Config c = obj.getClass().getAnnotation(Config.class);
            if (c.configLocation().isEmpty()) {
                throw new IllegalArgumentException("No location specified for config!");
            }
            return readConfig(obj, c.configLocation());
        } else {
            throw new IllegalArgumentException("Target object is not configureable!");
        }
    }

    public static <T> void writeConfig(T obj) {
        if (obj.getClass().isAnnotationPresent(Config.class)) {
            Config c = obj.getClass().getAnnotation(Config.class);
            if (c.configLocation().isEmpty()) {
                throw new IllegalArgumentException("No location specified for config!");
            }
            writeConfig(obj, c.configLocation());
        } else {
            throw new IllegalArgumentException("Target object is not configureable!");
        }
    }

    public static <T> T readConfig(T obj, String location) {
        return readConfig(obj, new File(location));
    }

    public static <T> void writeConfig(T obj, String location) {
        writeConfig(obj, new File(location));
    }

    public static <T> T readConfig(T obj, File file) {
        ConfigFile f = new ConfigFile(file);
        f.read();
        fillConfig(obj, f.getValueMap());
        return obj;
    }

    public static <T> void writeConfig(T obj, File file) {
        ConfigFile f = new ConfigFile(file);
        f.setValueMap(fetchConfig(obj));
        f.write();
    }

    protected static <T> T fillConfig(T obj, Map<String, String> map) {
        if (obj.getClass().isAnnotationPresent(Config.class)) {
            try {
                for (Field f : obj.getClass().getDeclaredFields()) {
                    if (f.isAnnotationPresent(ConfigValue.class)) {
                        ConfigValue v = f.getAnnotation(ConfigValue.class);
                        String name = v.name().isEmpty() ? f.getName() : v.name();
                        String value = map.get(name);
                        if (value == null) {
                            if (!v.defaultValue().isEmpty()) {
                                value = v.defaultValue();
                            } else if (v.required()) {
                                throw new RuntimeException("Value " + name + " is not in config!");
                            }
                        }
                        if (!f.isAccessible()) f.setAccessible(true);
                        f.set(obj, TypeConverter.convertTo(value, f.getType()));
                        }
                    }

            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new RuntimeException("Something went wrong while using reflection");
            }
        } else {
            throw new IllegalArgumentException("Target object is not configureable!");
        }
        return obj;
    }


    protected static <T> Map<String, String> fetchConfig(T obj) {
        HashMap<String, String> ret = new HashMap<String, String>();
        try {
            if (obj.getClass().isAnnotationPresent(Config.class)) {
                for (Field f : obj.getClass().getDeclaredFields()) {
                    if (f.isAnnotationPresent(ConfigValue.class)) {
                        if (!f.isAccessible()) f.setAccessible(true);
                        ConfigValue v = f.getAnnotation(ConfigValue.class);
                        String name = v.name().isEmpty() ? f.getName() : v.name();
                        String value="";
                        if(f.get(obj)==null){
                            if(!v.defaultValue().isEmpty()){
                                value = v.defaultValue();
                            }else if(v.required()){
                                throw new RuntimeException("Value "+name+" is not in config!");
                            }else{
                                value="null";
                            }
                        }else{
                            value = TypeConverter.convertTo(f.get(obj), String.class);
                        }
                        ret.put(name, value);
                    }
                }

            } else {
                throw new IllegalArgumentException("Target object is not configureable!");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("Something went wrong while using reflection");
        }

        return ret;
    }
}

package net.escendia.config.configs;

import net.escendia.config.annotation.Config;
import net.escendia.config.annotation.ConfigValue;

import java.util.List;
import java.util.Map;

@Config(configLocation = "src/test/resources/TestConfig.txt")
public class TestConfig {

    @ConfigValue(name = "name",required = true, defaultValue = "defaultValue")
    private String name;

    @ConfigValue(name = "list",required = false)
    private List<List<String>> list;

    @ConfigValue(name = "map",required = false)
    private Map<String, Map<String, String>> map;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<List<String>> getList() {
        return list;
    }

    public void setList(List<List<String>> list) {
        this.list = list;
    }

    public Map<String, Map<String, String>> getMap() {
        return map;
    }

    public void setMap(Map<String, Map<String, String>> map) {
        this.map = map;
    }
}

package net.escendia.config.configs;

import net.escendia.config.Main;
import net.escendia.config.converter.TypeConverter;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ConfigManagerTest extends Main {



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAgetInstance() {
        Assert.assertNotNull(ConfigManager.getInstance());
    }

    @Test
    public void testBcreateConfig(){
        String testName = "TestName";
        List<String> testList2 = Arrays.asList("ListElement1", "ListElement2");
        List<List<String>> testList1 = Arrays.asList(testList2);
        Map<String, Map<String, String>> testMap1 = new HashMap<>();
        Map<String, String> testMap2 = new HashMap<>();
        testMap2.put("key1m2", "={keyvalue1m2}");
        testMap2.put("key2m2", "keyvalue2m2");

        testMap1.put("key1root", testMap2);

        TestConfig testConfig = new TestConfig();
        testConfig.setName(testName);
        testConfig.setList(testList1);
        testConfig.setMap(testMap1);

        ConfigManager.writeConfig(testConfig);
    }

    @Test
    public void testCcreateConfig(){
        TestConfig testConfig = ConfigManager.readConfig(new TestConfig());
        Assert.assertEquals(testConfig.getName(),"TestName");
        Assert.assertEquals(testConfig.getMap().size(),1);
        Assert.assertEquals(testConfig.getList().size(),1);
    }
}
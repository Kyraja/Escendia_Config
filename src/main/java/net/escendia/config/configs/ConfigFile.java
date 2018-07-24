package net.escendia.config.configs;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ConfigFile {
    File file;
    Map<String, String> values;

    public ConfigFile(File file) {
        this.file = file;
    }

    public void read() {
        values = new HashMap<String, String>();
        if (!file.exists()) return;
        try {
            BufferedReader read = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str;
            while ((str = read.readLine()) != null) {
                int i = str.indexOf("=");
                if (i == -1) continue;
                values.put(str.substring(0, i), str.substring(i + 1, str.length()));
            }
            read.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write() {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(new FileOutputStream(file));
            for (Map.Entry<String, String> entry : values.entrySet()) {
                out.println(entry.getKey() + "=" + entry.getValue());
            }
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> getValueMap() {
        return values;
    }

    public void setValueMap(Map<String, String> values) {
        this.values = values;
    }
}

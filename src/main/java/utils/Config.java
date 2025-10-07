package utils;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties p = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath!");
            }
            p.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String get(String key) {
        String sys = System.getProperty(key);
        return sys != null ? sys : p.getProperty(key);
    }
}



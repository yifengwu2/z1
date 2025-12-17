package com.j;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputFilter;
import java.util.Properties;

public class ConfigLoader {
    public static Properties load() {
        Properties properties = new Properties();
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("config.txt")) {
            if (in == null) {
                System.out.println("配置文件不存在");
                return properties;
            }
            properties.load(in);
            System.out.println("配置加载成功");
            return properties;
        } catch (IOException e) {
            System.out.println("获取失败" + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        Properties pro = ConfigLoader.load();
        String value = pro.getProperty("name", ".");
        System.out.println(value);

    }
}

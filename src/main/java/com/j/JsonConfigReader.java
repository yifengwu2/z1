package com.j;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class JsonConfigReader {
    // 存储从 JSON 读出来的值
    private String directory;
    private String keyword;
    private String output;
    private boolean ignoreCase;

    /**
     * 从指定路径加载 config.json
     */
    public boolean load(String configPath) {
        try {
            //💡 因为 resources 目录下的文件，在编译后会被打包进 JAR 文件或类路径（classpath）中。
            //所以它不再是普通文件系统路径，而是“类路径资源”（classpath resource）。
            //读取文件内容为字符串
//            String content = Files.readString(Path.of(configPath));
            try (InputStream in = JsonConfigReader.class.getClassLoader().getResourceAsStream(configPath)) {
                System.out.println("📃文件配置内容");
                if (in == null) {
                    System.err.println("❌ 找不到 config.json，请检查是否在 resources 目录下！");
                    return false;
                }
                // 2️⃣ 将输入流转为字符串
                String content;
                try (Scanner sc = new Scanner(in, StandardCharsets.UTF_8)) {
                    // \A 表示匹配输入的开始，相当于把整个流读成一个字符串
                    content = sc.useDelimiter("\\A").next();

                }
                // 解析成 JSONObject（结构化数据）
                JSONObject json = new JSONObject(content);

                directory = json.getString("directory");
                keyword = json.getString("keyword");
                output = json.getString("output");
                ignoreCase = json.getBoolean("ignoreCase");

                if (directory.isEmpty() || directory.trim().isEmpty()) {
                    System.err.println("文件目录不能为空");
                    return false;
                }

                if (keyword.isEmpty() || keyword.trim().isEmpty()) {
                    System.err.println("内容不能为空");
                    return false;
                }
                System.out.println("配置加载成功✅");
                return true;
            }

        } catch (IOException e) {
            System.out.println("配置读取失败" + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        JsonConfigReader jsonConfigReader = new JsonConfigReader();
        System.out.println(jsonConfigReader.load("config.json"));
        System.out.println(jsonConfigReader.directory);
        System.out.println(jsonConfigReader.keyword);
        System.out.println(jsonConfigReader.output);
    }


}

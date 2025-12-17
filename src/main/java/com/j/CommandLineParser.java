package com.j;

import java.util.HashMap;
import java.util.Map;

public class CommandLineParser {
    private String directory;
    private String keyword;
    private String output;

    public CommandLineParser(String[] args) {
        parse(args);
    }

    private void parse(String[] args) {
        int n = args.length;
        int i = 0;
        while (i < n) {
            if (args[i].equals("--directory") || args[i].equals("--dir")) {
                if (isHasNext(i, args)) {
                    this.directory = args[i + 1];
                    i += 2;
                } else {
                    throw new IllegalArgumentException("错误：--directory 缺少目录路径！");
                }
            } else if (args[i].equals("--keyword") || args[i].equals("--word")) {
                if (isHasNext(i, args)) {
                    this.keyword = args[i + 1];
                    i += 2;
                } else {
                    throw new IllegalArgumentException("缺少关键词-word");
                }
            } else if (args[i].equals("--output")) {
                if (isHasNext(i, args)) {
                    this.output = args[i + 1];
                    i += 2;
                } else {
                    throw new IllegalArgumentException("缺少关键词-output");
                }
            } else {
                throw new IllegalArgumentException("参数格式错误：如没有--");
            }
        }
    }

    private boolean isHasNext(int i, String[] args) {
        return i + 1 < args.length && !args[i + 1].startsWith("--");
    }

    public String getDirectory() {
        return directory;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getOutput() {
        return output;
    }

}

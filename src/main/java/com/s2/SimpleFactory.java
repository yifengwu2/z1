package com.s2;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

interface Logger {
    void log();
}

//控制台日志
@Slf4j
@NoArgsConstructor
class ConsoleLogger implements Logger {
    @Override
    public void log() {
        log.debug("{}开始记录日志...", ConsoleLogger.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "ConsoleLogger{}";
    }
}

//文件日志
@Slf4j
@NoArgsConstructor
class FileLogger implements Logger {
    @Override
    public void log() {
        log.debug("{}开始记录日志...", FileLogger.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "FileLogger{}";
    }
}

//远程日志
@Slf4j
@NoArgsConstructor
class RemoteLogger implements Logger {
    @Override
    public void log() {
        log.debug("{}开始记录日志...", RemoteLogger.class.getSimpleName());
    }

    @Override
    public String toString() {
        return "RemoteLogger{}";
    }
}

@Slf4j
@NoArgsConstructor
public class SimpleFactory {
    private final static Map<String, Class<? extends Logger>> map = new HashMap<>();

    static {
        map.put("dev", ConsoleLogger.class);
        map.put("test", FileLogger.class);
        map.put("prod", RemoteLogger.class);
    }

    public static Logger create(String s) {
        Class<? extends Logger> clazz = map.get(s);
        if (clazz == null) {
            throw new IllegalArgumentException("没有相应的日志器");
        }
        try {
            log.debug("正在创建{},日志器", clazz.getSimpleName());
            return clazz.getConstructor().newInstance();
        } catch (Exception e) {
            String msg = "创建日志器" + clazz.getSimpleName() + "失败";
            log.error(msg, e);
            throw new RuntimeException(msg, e);
        }
    }

    public static void main(String[] args) {
        Logger logger = SimpleFactory.create("dev");
        logger.log();

    }
}

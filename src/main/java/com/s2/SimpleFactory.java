package com.s2;


import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 简单工厂
 */
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
    private final static Map<String, Supplier<Logger>> map = new HashMap<>();

    static {
        map.put("dev", ConsoleLogger::new);
        map.put("test", FileLogger::new);
        map.put("prod", RemoteLogger::new);
    }

    public static Logger create(String s) {
        Supplier<Logger> supplier = map.get(s);
        if (supplier == null) {
            throw new IllegalArgumentException("没有获取到相应的日志器");
        }
        return supplier.get();
    }

    public static void main(String[] args) {
        Logger logger = SimpleFactory.create("test");
        logger.log();

    }
}

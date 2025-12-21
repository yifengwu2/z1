package com.s2;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 适配器模式
 * 适配器真正的职责，不只是“转发指令”，更是：
 * “听懂新话 → 转成老动作 → 看着老机器做完 → 把结果翻译回新话，原样奉还。”
 */
public class AdaptTest {
    public static void main(String[] args) {
        OldMachine oldMachine = new OldMachine("老式机器");
        IntelligentAdapt intelligentAdapt = new IntelligentAdapt(oldMachine);
        System.out.println(intelligentAdapt.protocol("xxxx", 2));
    }
}

/**
 * 适配器
 */
interface DeviceProtocol {
    //接受协议
    Content protocol(String msg, int i);
}

@Slf4j(topic = "IntelligentAdapt")
class IntelligentAdapt implements DeviceProtocol {
    private OldMachine oldMachine;

    public IntelligentAdapt(OldMachine oldMachine) {
        this.oldMachine = oldMachine;
    }

    //即接收了协议，又调整了按钮
    @Override
    public Content protocol(String msg, int i) {
        //其实这里不需要传i还有一种可能就是解析这个msg
//        oldMachine.getInstruction(i);
        log.debug("协议内容{}", msg);
        if (oldMachine.getInstruction(i)) {
            return new Content(Result.SUCCESS, i, LocalDateTime.now());
        }
        return new Content(Result.FAIL, i, LocalDateTime.now());
    }
}

/**
 * 返回的协议内容
 */
@Getter
class Content {
    private final Result status;
    //封装杯数
    private final int sealed;
    private final LocalDateTime timestamp;

    public Content(Result statue, int sealed, LocalDateTime localDateTime) {
        this.status = statue;
        this.sealed = sealed;
        this.timestamp = localDateTime;
    }
    private String format(){
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return timestamp.format(pattern);
    }

    @Override
    public String toString() {
        return String.format(
                "{\"status\":\"%s\",\"sealed\":%d,\"timestamp\":\"%s\"}",
                status.name(), sealed, format()
        );
    }
}

/**
 * 老式机器
 */
@Slf4j(topic = "OldMachine")
class OldMachine {
    private String name;

    public OldMachine(String name) {
        this.name = name;
    }

    public boolean getInstruction(int i) {
        if (i == 1) {
            log.debug("封普通杯（纸杯）");
        } else if (i == 2) {
            log.debug("封厚杯（塑料杯）");
        } else if (i == 3) {
            log.debug(" 封冰杯（带冰格的特种杯）");
        } else {
            log.debug("没有合适的类型");
            return false;
        }
        return true;
    }


    @Override
    public String toString() {
        return "OldMachine{" +
                "name='" + name + '\'' +
                '}';
    }
}

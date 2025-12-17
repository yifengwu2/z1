package com.l;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

@Slf4j(topic = "Test06")
public class Test06 {
    public static void main(String[] args) {
//        GuardedObject guardedObject = new GuardedObject();
//        new Thread(() -> {
//            //等待结果
//            log.debug("等待结果");
//            List<String> response = (List<String>) guardedObject.get();
//            log.debug("结果是大小{}", response.size());
//
//        }, "t1").start();
//
//        new Thread(() -> {
//            log.debug("执行下载");
//            ;
//            try {
//                List<String> list = Downloader.download();
//                guardedObject.complete(list);
//            } catch (IOException e) {
//                System.out.println(e.getMessage());
//            }
//
//        }, "t2").start();
        for (int i = 0; i < 3; i++) {
            new People().start();
        }
        Sleeper.sleep(1);
        for (Integer id : Mailboxes.getIds()) {
            new PostMan(id, "内容" + id).start();
        }

    }

    static class Sleeper {
        public static void sleep(int time) {
            try {
                Thread.sleep(time * 1000L);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

@Slf4j(topic = "Person")
class People extends Thread {
    //收信
    @Override
    public void run() {
        GuardedObject guardedObject = Mailboxes.createGuardedObject();
        log.debug("收信id{}", guardedObject.getId());
        Object mail = guardedObject.get(5000);
        log.debug("收到信ID{},内容{}", guardedObject.getId(), mail);
    }
}

@Slf4j(topic = "PostMan")
class PostMan extends Thread {
    private int id;
    private String mail;

    public PostMan(int id, String mail) {
        this.id = id;
        this.mail = mail;
    }

    @Override
    public void run() {
        GuardedObject guardObject = Mailboxes.getGuardObject(id);
        log.debug("送信id{}", guardObject.getId());
        guardObject.complete(mail);

    }
}

class Mailboxes {
    private static Map<Integer, GuardedObject> boxes = new Hashtable<>();
    private static int id;

    public synchronized static int generatorId() {
        id++;
        return id;
    }

    public static GuardedObject createGuardedObject() {
        int id = generatorId();
        GuardedObject go = new GuardedObject(id);
        boxes.put(id, go);
        return go;
    }

    public static Set<Integer> getIds() {
        return boxes.keySet();
    }

    public static Map<Integer, GuardedObject> getMap() {
        return boxes;
    }

    public static GuardedObject getGuardObject(int id) {
        return boxes.get(id);
    }


}

//增加超时效果
class GuardedObject {
    //标识Guarded Object
    private int id;

    public GuardedObject(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    //结果
    private Object response;

    //获取结果
    public Object get(long timeout) {
        synchronized (this) {
            //开始时间
            long begin = System.currentTimeMillis();
            //经历时间
            long parseTime = 0;
            while (response == null) {
                long waitTime = timeout - parseTime;
                if (waitTime <= 0) {
                    break;
                }
                try {
                    this.wait(waitTime);//虚假唤醒
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                parseTime = System.currentTimeMillis() - begin;
            }
        }
        return response;

    }

//    public Object get() {
//        synchronized (this) {
//            //没有结果
//            while (response == null) {
//                try {
//                    this.wait();
//                } catch (InterruptedException e) {
//                    System.out.println(e.getMessage());
//                }
//            }
//        }
//        return response;
//    }

    //产生结果
    public void complete(Object object) {
        synchronized (this) {
            //给结果成员变量赋值
            this.response = object;
            this.notifyAll();
        }
    }


}

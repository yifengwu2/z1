package com.s2;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 被观察者
 */
class Subject {
    //维护当前开关状态
    private final AtomicBoolean enabled;
    private final CopyOnWriteArrayList<Watch> watches;

    public Subject() {
        this.enabled = new AtomicBoolean(false);
        this.watches = new CopyOnWriteArrayList<>();
    }

    public void enable() {
        if (enabled.compareAndSet(false, true)) {
            notifyWatchers(true);
        }
    }

    public void disable() {
        if (enabled.compareAndSet(true, false)) {
            notifyWatchers(false);
        }
    }

    public void notifyWatchers(boolean enable) {
        for (Watch watch : watches) {
            watch.onPolicyChange(enable);
        }
    }

    public void add(Watch watch) {
        watches.add(watch);
    }

    public void remove(Watch watch) {
        watches.remove(watch);
    }

    public boolean isEnabled() {
        return enabled.get();
    }
}

interface Watch {
    void onPolicyChange(boolean enabled);
}


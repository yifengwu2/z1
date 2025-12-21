package com.s2;

public enum Result {
    SUCCESS(400,"成功"),FAIL(500,"失败");
    private final int i;
    private final String msg;

    Result(int i, String msg) {
        this.i = i;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Result{" +
                "i=" + i +
                ", msg='" + msg + '\'' +
                '}';
    }
}

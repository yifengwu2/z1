package com.s3;

public enum Status {
    IDLE,// 空闲（可被锁定）
    LOCKED, // 已锁定（30分钟有效期，未签到将自动释放）
    Full,// 已签到（永久占用）
    MAINTENANCE;
}

package com.s3;

public enum LimitResult {
    //允许，拒绝，重试
    ALLOWED,REJECTED,RETRY_AFTER();

    LimitResult() {
    }
}

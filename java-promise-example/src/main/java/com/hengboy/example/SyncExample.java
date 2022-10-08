package com.hengboy.example;

import lombok.extern.slf4j.Slf4j;
import org.riversun.promise.Func;
import org.riversun.promise.SyncPromise;

/**
 * @author 恒宇少年
 */
@Slf4j
public class SyncExample {
    public static void main(String[] args) {
        Func func1 = (action, data) -> {
            new Thread(() -> {
                log.info("Process-1");
                action.resolve();
            }).start();

        };
        Func func2 = (action, data) -> {
            new Thread(() -> {
                log.info("Process-2");
                action.resolve();
            }).start();

        };
        SyncPromise.resolve()
                .then(func1)
                .then(func2)
                .start();
        log.info("Hello,Promise");
    }
}

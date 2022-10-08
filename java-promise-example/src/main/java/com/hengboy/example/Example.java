package com.hengboy.example;

import lombok.extern.slf4j.Slf4j;
import org.riversun.promise.Func;
import org.riversun.promise.Promise;

/**
 * @author 恒宇少年
 */
@Slf4j
public class Example {
    public static void main(String[] args) {
        Func function1 = (action, data) -> {
            log.info("Process-1");
            action.resolve("Result-1");
        };

        Func function2 = (action, data) -> {
            log.info("Process-2 result=" + data);
            action.resolve();
        };
        Func rejectFunc = (action, data) -> {
            log.info("rejectFunc result=" + data);
            action.resolve();
        };

        Promise.resolve()
                .then(new Promise(function1))
                .then(function2, rejectFunc)
                .start();// start Promise operation

        log.info("Hello,Promise");
    }
}

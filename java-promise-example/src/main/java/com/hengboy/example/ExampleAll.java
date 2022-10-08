package com.hengboy.example;

import lombok.extern.slf4j.Slf4j;
import org.riversun.promise.Func;
import org.riversun.promise.Promise;

import java.util.List;

/**
 * @author 恒宇少年
 */
@Slf4j
public class ExampleAll {
    public static void main(String[] args) {
        Func func1 = (action, data) -> {
            Promise.sleep(1000);
            log.info("func1 running");
            action.resolve("func1-result");
        };
        Func func2 = (action, data) -> {
            Promise.sleep(500);
            log.info("func2 running");
            action.resolve("func2-result");
        };
        Func func3 = (action, data) -> {
            Promise.sleep(1500);
            log.info("func3 running");
            action.resolve("func3-result");
        };
        Func funcGetResult = (action, data) -> {
            List<Object> resultList = (List<Object>) data;
            for (int i = 0; i < resultList.size(); i++) {
                Object o = resultList.get(i);
                log.info("No." + (i + 1) + " result is " + o);
            }
            action.resolve();
        };
        Promise.all(func1, func2, func3)
                .always(funcGetResult)
                .start();
        log.info("Start ExampleAll.");
    }
}

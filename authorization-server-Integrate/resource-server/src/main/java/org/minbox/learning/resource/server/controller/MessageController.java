package org.minbox.learning.resource.server.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息资源控制器
 *
 * @author 恒宇少年
 */
@RestController
@RequestMapping(value = "/message")
public class MessageController {
    @GetMapping("/read")
    public String[] getMessages() {
        return new String[]{"Message 1", "Message 2", "Message 3"};
    }
}

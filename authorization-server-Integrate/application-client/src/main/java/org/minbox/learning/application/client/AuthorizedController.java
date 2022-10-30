package org.minbox.learning.application.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 恒宇少年
 */
@RestController
public class AuthorizedController {

    @GetMapping("/authorized")
    public String authorized(String code) {
        return code;
    }
}

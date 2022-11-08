package org.minbox.learning.keycloak.springsecurity.client.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author 恒宇少年
 */
@RestController
@RequestMapping(value = "/admin")
public class AdminController {

    @GetMapping
    public String index(Principal principal) {
        return principal.getName();
    }
}

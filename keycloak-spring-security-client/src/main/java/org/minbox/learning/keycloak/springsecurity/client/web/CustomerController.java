package org.minbox.learning.keycloak.springsecurity.client.web;

import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;

/**
 * @author 恒宇少年
 */
@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    @GetMapping
    public Collection<GrantedAuthority> index(Principal principal) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) principal;
        return authenticationToken.getAuthorities();
    }
}

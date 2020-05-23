package org.kee.security.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/16 0:01
 */
@Service
public class MethodSecurityService {

    @PreAuthorize("hasAnyRole('admin')")
    public String admin() {
        return "HELLO ADMIN";
    }

    @Secured("ROLE_user")
    public String user() {
        return "HELLO USER";
    }

    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String all() {
        return "HELLO All";
    }
}

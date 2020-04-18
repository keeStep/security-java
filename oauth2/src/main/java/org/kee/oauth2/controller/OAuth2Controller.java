package org.kee.oauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/4/18 22:53
 */
@RestController
public class OAuth2Controller {

    @GetMapping("/admin/kee")
    public String admin() {
        return "HERE IS ADMIN";
    }
    @GetMapping("/user/kee")
    public String user() {
        return "HERE IS USER";
    }
    @GetMapping("/kee")
    public String kee() {
        return "I'M KEE";
    }
}

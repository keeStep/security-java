package org.kee.securitydb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/16 2:44
 */
@RestController
public class UserController {

    @GetMapping("user")
    public String access() {
        return "ACCESS";
    }

    @GetMapping("/dba/hello")
    public String dba() {
        return "dba ACCESS";
    }

    @GetMapping("/admin/hello")
    public String admin() {
        return "admin ACCESS";
    }

    @GetMapping("/user/hello")
    public String user() {
        return "user ACCESS";
    }
}

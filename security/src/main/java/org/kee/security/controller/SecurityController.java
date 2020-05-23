package org.kee.security.controller;

import org.kee.security.service.MethodSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/15 22:21
 */
@RestController
public class SecurityController {
    @Autowired
    private MethodSecurityService methodSecurityService;

    @GetMapping("1")
    public String hello(){
        return "HELLO SECURITY!!";
    }

    @GetMapping("admin")
    public String admin(){
        return "HELLO ADMIN!!";
    }

    @GetMapping("user")
    public String user(){
        return "HELLO USER!!";
    }

    @GetMapping("login")
    public String login(){
        return "PLEASE DO LOGIN";
    }


    /**
     * 测试方法的安全校验
     * @return string
     */
    @GetMapping("kee1")
    public String kee1(){
        return methodSecurityService.admin();
    }

    @GetMapping("kee2")
    public String kee2(){
        return methodSecurityService.user();
    }

    @GetMapping("kee3")
    public String kee3(){
        return methodSecurityService.all();
    }
}

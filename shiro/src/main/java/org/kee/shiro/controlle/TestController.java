package org.kee.shiro.controlle;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/4/19 23:47
 */
@RestController
public class TestController {

    @GetMapping("/login")
    public String login() {
        return "PLEASE LOGIN";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password) {
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username, password));
        } catch (AuthenticationException e) {
            return "LOGIN FAILED";
        }
        return "LOGIN SUCCESS";
    }

    @GetMapping("/p/add")
    public String add() {
        return "ADD DATA SUCCESS";
    }
}

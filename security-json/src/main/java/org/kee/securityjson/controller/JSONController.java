package org.kee.securityjson.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author： 羽子少年
 * @Date： 2020/5/23 15:28
 */
@RestController
public class JSONController {

    /*
     登录的接口默认为 /login
     */

    /**
     * 受保护资源接口
     * @return message
     */
    @GetMapping("/1")
    public String hero() {
        return "HELLO! YOU ARE HERO";
    }
}

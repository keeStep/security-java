package org.kee.securityjson.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author： 羽子少年
 * @Date： 2020/5/23 16:05
 */
@Component
public class MyAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {
        logger.info("登录成功");
        response.setContentType("application/json;charset=UTF-8");
        // 登录成功后把 authentication 对象信息返回，返回示例如下
        response.getWriter().write( objectMapper.writeValueAsString(authentication));

        /**
         * {
         *     "authorities": [],
         *     "details": {
         *         "remoteAddress": "127.0.0.1",
         *         "sessionId": "673F46041CE4A94A9CDAD910B2D534C8"
         *     },
         *     "authenticated": true,
         *     "principal": {
         *         "password": null,
         *         "username": "kee",
         *         "authorities": [],
         *         "accountNonExpired": true,
         *         "accountNonLocked": true,
         *         "credentialsNonExpired": true,
         *         "enabled": true
         *     },
         *     "credentials": null,
         *     "name": "kee"
         * }
         */
    }
}

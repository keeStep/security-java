package org.kee.securityjson.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * @Author： 羽子少年
 * @Date： 2020/5/23 15:03
 */
public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * 为什么要复写这个filter的该方法？
     * 
     * 因 UsernamePasswordAuthenticationFilter 获取参数的方式是不支持JSON的，所以改造下
     * @param req
     * @param resp
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse resp) throws AuthenticationException {
        // 父类的判断：登录的请求必须为 POST
        if (!req.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + req.getMethod());
        }

        // 用户登录以 JSON 格式传递参数时
        if (MediaType.APPLICATION_JSON_VALUE.equals(req.getContentType())) {
            String username = null;
            String password = null;

            try {
                Map<String, String> map = new ObjectMapper().readValue(req.getInputStream(), Map.class);
                username = map.get("username");
                password = map.get("password");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (username == null) {
                username = "";
            }

            if (password == null) {
                password = "";
            }

            username = username.trim();

            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    username, password);

            // Allow subclasses to set the "details" property
            setDetails(req, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
        }

        return super.attemptAuthentication(req, resp);
    }
}

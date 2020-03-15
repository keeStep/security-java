package org.kee.security.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/15 22:23
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true) // prePostEnabled 表示是否开启 方法前及方法后的校验
public class MultiHttpSecurityConfig {

    @Bean
    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); //密码不加密
        return new BCryptPasswordEncoder(); // 密码加密
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
//                .withUser("羽子少年").password("666").roles("admin") // 不加密时匹配的密码-明文匹配明文
//                .withUser("keestep").password("000").roles("user"); // 同上
                .withUser("羽子少年").password("$2a$10$/OGqLaLqOsbqUlAdxi1IH.icLe36qaFXLk1pMXMFZl7yJByZae5j2").roles("admin")
                .and()
                .withUser("keestep").password("$2a$10$3hx.QlOLaNdz/DWoKWsIxedKJAx3V0/0i95slfQLhIeRTswEM5sY2").roles("user");
    }


    @Configuration
    @Order(1) // 优先级；1最大。默认值为100，且不能重复，所以当有多个配置（WebSecurityConfigurerAdapter）时，最多只能有一个不加此注解
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");
        }
    }

    @Configuration
    @Order(2)
    public static class UserSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/user/**").authorizeRequests().anyRequest().hasRole("user");
        }
    }

    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("/doLogin") // 接口类型 POST
                    .successHandler((req, resp, auth) -> {
                        resp.setContentType("application/json;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("code", 200);
                        map.put("success", true);
                        map.put("msg", "恭喜您，登录成功");

                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    })
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}

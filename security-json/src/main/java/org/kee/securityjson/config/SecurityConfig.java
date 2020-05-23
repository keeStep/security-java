package org.kee.securityjson.config;

import org.kee.securityjson.filter.MyAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author： 羽子少年
 * @Date： 2020/5/23 15:20
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAuthenticationFailureHandler myAuthenticationFailureHandler;



    @Bean
    MyAuthenticationFilter myAuthenticationFilter() throws Exception {
        MyAuthenticationFilter filter = new MyAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());

        // TODO 这里可以自定义登陆成功或失败要显示的信息或页面
        filter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);
        return filter;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable();

        // 自定义的JSON数据处理拦截器放入到 security 的拦截器
        http.addFilterAt(myAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}

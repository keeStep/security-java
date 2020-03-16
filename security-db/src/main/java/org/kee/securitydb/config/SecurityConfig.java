package org.kee.securitydb.config;

import org.kee.securitydb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author： 羽子少年
 * @Date：  2020/3/16 2:40
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Q：干嘛的？？是认证成功然后稀里糊涂的转到error页面403的原因吗？
     * A: 并不是！！
     *
     *    ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓
     * ##角色继承##  【适用于boot 2.1.0以后版本】
     *
     * 和boot 2.0.8及之前版本的区别是：不是 \n 是空格
     * @return RoleHierarchy
     */
    @Bean
    RoleHierarchy roleHierarchy() {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy  ="ROLE_dba > ROLE_admin \n ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
        // return null; // 这个坑(doc注释的Q原因在这里)，害我熬了两天夜
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/dba/**").hasRole("dba") //root
                .antMatchers("/admin/**").hasRole("admin") //root  admin
                .antMatchers("/user/**").hasRole("user") // kee
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();
    }
}

package org.kee.shiro.config;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.kee.shiro.realm.MyRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author： 羽子少年
 * @Date： 2020/4/19 23:40
 */
@Configuration
public class ShiroConfig {

    @Bean
    MyRealm myRealm() {
        return new MyRealm();
    }

    @Bean
    SecurityManager securityManager() {
        // 下面这个注释的类不对，会报异常，注意下
//        DefaultSecurityManager manager = new DefaultSecurityManager();
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean() {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        bean.setLoginUrl("/login");
        bean.setSuccessUrl("index");
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin", "anon");
        // TODO 这个 auth 还不能瞎给，要给 authc ；待研究为什么
        map.put("/**", "authc");
        bean.setFilterChainDefinitionMap(map);

        return bean;
    }
}

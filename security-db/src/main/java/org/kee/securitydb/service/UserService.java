package org.kee.securitydb.service;

import org.kee.securitydb.bean.User;
import org.kee.securitydb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/16 2:17
 */
@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (null == user) {
            throw new UsernameNotFoundException("用户不存在啊呐呐");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
    }
}

package org.kee.securitydb.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.kee.securitydb.bean.Role;
import org.kee.securitydb.bean.User;

import java.util.List;

/**
 * @Author： 羽子少年
 * @Date： 2020/3/16 2:17
 */
@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getUserRolesById(Integer id);
}

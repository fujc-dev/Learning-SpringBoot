package com.zc58s.springbootauth2.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/17.
 *
 * @author szy
 * @since 1.0
 */
@Component("myUserDetailsService")
public class MyUserDetailsServiceImpl implements UserDetailsService {


    /**
     * 从数据库，或者其他的地方，查询用户的密码。角色，然后构建出{@link  UserDetails}对象。
     * <p>
     * 后续的话，由框架去检测密码的正确性，当然我们也可以自己实现{@link PasswordEncoder}，然验证数据的密码值与客户端传递的值是否正确
     * </p>
     * <p>
     *
     * </p>
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userAccount = "123456";
        //如果只做测试，没有自己的业务类，密码可以用固定的123456代替
        return new User(
                // 用户名
                username,
                // 密码
                userAccount,
                // 权限集合
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
    }
}

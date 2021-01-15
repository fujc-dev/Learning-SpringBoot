package com.zc58s.springbootauth2.security.jwt;

import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created on 2018/7/17. 配置密码加密方式，用来JWT验证用户密码
 *
 * @author szy
 * @since 1.0
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return hashPwd(rawPassword.toString(), "");
    }

    /**
     *  默认不做密码检查。
     * @param rawPassword
     * @param encodedPassword
     * @return
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        //这个方法其实就是验证加密后的密码，
        return true;
    }

    private String hashPwd(String pwd, String salt) {
        return new Sha256Hash(pwd, salt).toHex();
    }
}

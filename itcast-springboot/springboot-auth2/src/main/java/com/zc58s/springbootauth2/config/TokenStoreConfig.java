package com.zc58s.springbootauth2.config;

import com.zc58s.springbootauth2.properties.Oauth2Properties;
import com.zc58s.springbootauth2.security.jwt.MyJwtTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * Created on 2018/7/21 0021.
 *
 * @author szy
 * @email i@suntray.com
 * @since 1.0
 */
@Configuration
public class TokenStoreConfig {

  /** jwt TOKEN配置信息 */
  @Configuration
  @ConditionalOnProperty(
      prefix = "suntray.security.oauth2",
      name = "storeType",
      havingValue = "jwt",
      matchIfMissing = true)
  public static class JwtTokenCofnig {

    @Autowired private Oauth2Properties oAuth2Properties;

    /**
     * 使用jwtTokenStore存储token
     *
     * @return
     */
    @Bean("myJwtTokenStore")
    public TokenStore jwtTokenStore() {
      return new JwtTokenStore(jwtAccessTokenConverter());
    }

    /**
     * 用于生成jwt
     *
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
      JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter();
      accessTokenConverter.setSigningKey(oAuth2Properties.getJwtSigningKey()); // 生成签名的key
      return accessTokenConverter;
    }

    /**
     * 用于扩展JWT
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
    public TokenEnhancer jwtTokenEnhancer() {
      return new MyJwtTokenEnhancer();
    }
  }
}

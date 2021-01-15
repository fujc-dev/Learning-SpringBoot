package com.zc58s.springbootauth2.security.jwt;

import java.util.HashMap;
import java.util.Map;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

/**
 * Created on 2018/7/21 0021.
 *
 * @author szy
 * @email i@suntray.com
 * @since 1.0
 */
public class MyJwtTokenEnhancer implements TokenEnhancer {
  @Override
  public OAuth2AccessToken enhance(
      OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
    Map<String, Object> info = new HashMap<>(10);
    ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(info);
    return accessToken;
  }
}

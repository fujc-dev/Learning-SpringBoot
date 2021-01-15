package com.zc58s.springbootauth2.server;

import com.zc58s.springbootauth2.properties.Oauth2ClientProperties;
import com.zc58s.springbootauth2.properties.Oauth2Properties;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * Created on 2018/7/15 0015.
 *
 * @author szy
 * @email i@suntray.com
 * @since 1.0
 */
@Configuration
@EnableAuthorizationServer
public class MyAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private Oauth2Properties oAuth2Properties;

    @Autowired()
    @Qualifier("securityConfig")
    private WebSecurityConfigurerAdapter configurerAdapter;


    @Autowired
    @Qualifier("myUserDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("myJwtTokenStore")
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 用来配置授权（authrization）以及令牌（token）的访问断点和令牌服务。
     * <p>
     *
     * </p>
     *
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 刷新令牌时需要的认证管理和用户信息来源
        endpoints
                .tokenStore(tokenStore) //配置了令牌产生的方式
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                //AuthenticationManager，认证管理器
                //基于账号与密码的授权模式，必须要包含AuthenticationManager
                //而，这个认证管理器里面用于设置用户名与密码以及角色
                .authenticationManager(configurerAdapter.authenticationManagerBean())
                //授权的时候，集成密码验证，安防系统中，对获取token时，没有进行密码验证，那么。这个设置就可以直接删除了
                .userDetailsService(userDetailsService);
        // 扩展token返回结果
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancerList = new ArrayList();
            enhancerList.add(jwtTokenEnhancer);
            enhancerList.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancerList);
            // jwt
            endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 配置客户端一些信息
     * <p>
     * 如果要访问授权服务器，那么这个clientId与clientSecret必须要的。
     * </p>
     * <p>
     * 有点像，我们在开发小程序的时候，需要为小程序申请一个AppId和AppSecret一样。
     * </p>
     * <p>
     * 个人觉得，授权服务器，需要依赖这个AppId和AppSecret验证合法性或者正确性。
     * </p>
     * <p>
     * 安防系统，是在properties文件中，默认配置了两个客户端配置信息 。
     * 这玩意儿，客户端在获取授权的时候，必须要传到授权服务器中来的。
     * <p><i>suntray.security.oauth2.clients[0].clientId=suntray</i></p>
     * <p><i>suntray.security.oauth2.clients[0].clientSecret=suntray</i></p>
     * <p><i>suntray.security.oauth2.clients[1].clientId=professional</i></p>
     * <p><i>suntray.security.oauth2.clients[1].clientSecret=professional</i></p>
     * </p>
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder build = clients.inMemory();
        if (ArrayUtils.isNotEmpty(oAuth2Properties.getClients())) {
            for (Oauth2ClientProperties config : oAuth2Properties.getClients()) {
                build
                        .withClient(config.getClientId())
                        .secret(passwordEncoder.encode(config.getClientSecret()))
                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds())
                        .refreshTokenValiditySeconds(60 * 60 * 24 * 15) //这时间设置的也太长了
                        .authorizedGrantTypes(
                                "password",
                                "authorization_code",
                                "client_credentials",
                                "refresh_token")
                        .scopes("all");
            }
        }
    }

    /**
     * 安全检查流程，用来配置令牌端点（Token Endpoint）的安全与权限访问。
     * <p>
     * 配置令牌端点(Token Endpoint)的安全约束.
     * </p>
     *
     * <p>--tokenKeyAccess：</p>
     * <p>----默认值，禁用，denyAll</p>
     * <p>----permitAll</p>
     * <p>--checkTokenAccess：</p>
     * <p>----默认值，禁用，denyAll</p>
     * <p>---sAuthenticated</p>
     * <p>对以下的几个端点进行权限配置：</p>
     * <p>/oauth/authorize：授权端点</p>
     * <p>/oauth/token：令牌端点</p>
     * <p>/oauth/confirm_access：用户确认授权提交端点</p>
     * <p>/oauth/error：授权服务错误信息端点</p>
     * <p>/oauth/check_token：用于资源服务访问的令牌解析端点</p>
     * <p>/oauth/token_key：提供公有密匙的端点，如果使用JWT令牌的话</p>
     *
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 开启 /oauth/token_key接口可访问
        security.tokenKeyAccess("permitAll()");
        // 开启/oauth/check_token接口 可访问
        security.checkTokenAccess("isAuthenticated()");
    }
}

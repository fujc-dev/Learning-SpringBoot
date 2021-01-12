package com.zc58s.springbootbase.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置阿里巴巴的Druid连接池监控平台
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/1/12 8:58
 */
@Configuration
public class DruidConfiguration {


    /**
     * @Cofiguraion：Spring中有很多配置文件，文件中会配置很多Bean。在类上添加@Configuration注解，可以理解为该类变成一个XML配置文件。
     *
     * @Bean：等同于XML配置文件中的<bean></bean>配置。SpringBoot会把加上该注解的方法返回值装载进Spring容器中，方法的名称对应bean标签的id属性值。
     */

    /**
     * 暂时不知道啥意思，按照书上配置的。
     *
     * <p>用户名和密码：就是登录界面需要输入的；</p>
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "123456");
        //是否能够重置数据
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 暂时不知道啥意思，按照书上配置的
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则
        registrationBean.addUrlPatterns("/*");
        //添加需要忽略的格式信息
        registrationBean.addInitParameter("exclusions", "*.js,*gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return registrationBean;
    }
}

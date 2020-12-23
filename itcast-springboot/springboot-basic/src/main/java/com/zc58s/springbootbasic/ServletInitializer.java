package com.zc58s.springbootbasic;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * WebApplicationInitializer可以看做是Web.xml的替代，它是一个接口。
 * <p>
 *     通过实现WebApplicationInitializer，在其中可以添加servlet，listener等，在加载Web项目的时候会加载这个接口实现类，从而起到web.xml相同的作用。
 * </p>
 */
public class ServletInitializer extends SpringBootServletInitializer {

    /**
     *
     * @param application
     * @return
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpringbootBasicApplication.class);
    }

}



```text
 SpringMVC + JSP + Amaze UI
```

```text
    1、在使用idea集成jsp时，webapp里面的静态资源，不能放在配置的WEB-INF文件夹中；
    2、使用MyBatis框架时，
        mybatis.mapper-locations=classpath:mapper/*Mapper.xml
        mybatis.type-aliases-package=com.zc58s.springmvcdemo.entity
        mybatis.type-handlers-package=com.zc58s.springmvcdemo.handler
    3、@ComponentScan(basePackages = {"com.zc58s.springmvcdemo.*"})，这个扫描配置好像会影响controller的扫描。
        这个不单单是对MyBatis的配置，还涉及到controller之类的其他配置，对于我这个初学者，暂时还没有全部弄明白；
    4、 视图配置
        spring.mvc.view.prefix=/WEB-INF/jsp/
        spring.mvc.view.suffix=.jsp

```




### RequestMapping
```java

/**
* @RequestMapping注解所配置的内容，保存到处理器映射器中，然后等待http请求的到来，
* 然后，http请求通过拦截以及HandlerMapping匹配，然后找到对应的处理器，然后，将拦截器，处理保存到
* HandlerExecutionChain对象中，这个HandlerExecutionChain对象将会被返回到DispatcherServlet中，
* 通过DispatcherServlet的执行，完成请求处理，整个流程见：SpringMvc全流程.mdl
*
*/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Mapping
public @interface RequestMapping {
    //配置请求映射名称
    String name() default "";
    //通过路径映射
    @AliasFor("path")
    String[] value() default {};
    //通过路径映射回path配置项
    @AliasFor("value")
    String[] path() default {};
    //限定只响应HTTP请求：GET、POST、HEAD、OPTIONS、PUT、TRACE等
    //默认情况加可以响应所有类型请求
    RequestMethod[] method() default {};
    //当存在对应的HTTP参数时，才响应请求
    String[] params() default {};
    //限定请求头存在对应的参数时才响应
    String[] headers() default {};
    //限定HTTP请求提交类型，如："application/json"，"text/html"
    String[] consumes() default {};
    //限定返回的内容类型，仅当HTTP请求头中的（ACCEPT）类型中包含制定类型时才返回
    String[] produces() default {};
}

```
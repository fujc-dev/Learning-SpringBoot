

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
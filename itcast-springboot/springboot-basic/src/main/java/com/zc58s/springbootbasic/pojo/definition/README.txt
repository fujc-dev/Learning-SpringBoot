演示依赖注入

人依赖动物，比如：人依赖狗狗看门。依赖猫咪抓老鼠等等。

定义一个Person的接口，
addAnimal方法，让这个人依赖某一个Animal。
service 方法，通过依赖的Animal，通过这个Animal的能力，去完成某一件事情。

顶一个Animal的接口，包含一个use方法，该方法是狗狗的能力，狗有看门的能力，猫咪有抓老鼠的能力。


@Autowired

在使用Autowired作为依赖注入时，假如有两个子类的情况下，程序会报一个org.springframework.beans.factory.UnsatisfiedDependencyException的异常。
以程序中的猫狗为例，@Autowired不知道为BusinessPearson注入猫还是狗。
//Error creating bean with name 'businessPearson' defined in file [E:\MyProjects\Learning-SpringBoot\itcast-springboot\springboot-basic\target\classes\com\zc58s\springbootbasic\pojo\definition\impl\BusinessPearson.class]: Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type 'com.zc58s.springbootbasic.pojo.definition.Animal' available: expected single matching bean but found 2: cat,dog
就是说，我不知道到底该注入谁进去。


依赖注入案例：
    分析：
        @Autowired默认是按照类型进行匹配的。那么在本例中在使用@Autowired时，首先BusinessPearson需要注入的是一个Animal的接口，那么，
    @Autowired就会去容器里面找，类型是Animal的，此时找到两个Dog、Cat，但是程序不知道选择那个。
    解决方案：
            1、在可以将BusinessPearson中声明的Animal的成员变量的名称改为dog。或者cat，用于匹配需要注入的Animal.
            为什么？
            首先，@Autowired会根据类型去匹配。当匹配到多个满足条件的Bean对象时，它会优先通过属性名称与Bean对象匹配。
            当前通过这个属性的名称与Bean匹配，总感觉哪里不对。当然是有效的。可以将其理解为约定大约配置。
            2、另一种解决方法：
                通过将默认需要注入的类上添加一个@Primary注解。表示优先注入这个对象。
                假如，相同父类的几个类都标记了@Primary注解，那么还可以增加一个@Qualifier注解。
                如：
                   @Autowired
                   @Qualifier("cat")
                   Animal animal = null;  //这个就表示，注入的时候，默认注入毛，这个代码看起来就更优雅一下。
                   比直接将animal成员变量名称改成cat要美观得多。
                   美观的代码就是好代码。
            3、通过构造函数注入：
                如：
                @Autowired
                public BusinessPearson(@Qualifier("cat") Animal animal) {
                    this.animal = animal;
                }
                这是Spring推荐的写法。



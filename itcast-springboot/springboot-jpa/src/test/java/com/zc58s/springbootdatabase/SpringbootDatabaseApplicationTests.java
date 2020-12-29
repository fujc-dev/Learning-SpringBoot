package com.zc58s.springbootdatabase;

import com.zc58s.springbootjpa.dao.FactoryRepository;
import com.zc58s.springbootjpa.dao.M2MUserRepository;
import com.zc58s.springbootjpa.dao.PeopleRepository;
import com.zc58s.springbootjpa.dao.ProductRepository;
import com.zc58s.springbootjpa.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class SpringbootDatabaseApplicationTests {

    @Autowired
    private PeopleRepository peopleRepository;
    @Autowired
    private FactoryRepository factoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private M2MUserRepository userRepository;

    @Test
    void contextLoads() {
    }

    /**
     * 一对一新增：新增公民信息与关联的身份证信息
     */
    @Test
    void addPeopleAndIdcard() {
        //创建身份证信息
        Idcard idcard = new Idcard();
        idcard.setIdCardCode("123456789");

        //创建公民信息
        People people = new People();
        people.setName("pan_junbiao的博客");
        people.setSex("男");
        people.setAge(32);
        //将公民与身份证信息关联
        people.setIdcard(idcard);

        //执行新增操作
        peopleRepository.save(people);

        //如果新增成功，则可以获取自增主键
        //否则新增失败，则抛出异常
        if (people.getId() > 0) {
            System.out.println("新增公民信息成功！");
            System.out.println("公民ID：" + people.getId());
            System.out.println("公民名称：" + people.getName());
            System.out.println("身份证号码：" + idcard.getIdCardCode());
        }

    }

    /**
     * 一对一查询：获取公民信息与关联的身份证信息
     */
    @Test
    void getPeopleAndIdcard() {
        People people = peopleRepository.findById(1).get();
        if (people != null) {
            System.out.println("---------------1、公民信息--------------------");
            System.out.println("公民编号：" + people.getId());
            System.out.println("公民名称：" + people.getName());
            System.out.println("公民性别：" + people.getSex());
            System.out.println("公民年龄：" + people.getAge());

            //获取关联的身份证信息信息
            System.out.println("---------------2、身份证信息信息---------------");
            Idcard idCard = people.getIdcard();
            if (idCard != null) {
                System.out.println("身份证ID：" + idCard.getId());
                System.out.println("身份证号码：" + idCard.getIdCardCode());
            }
        }

    }

    /**
     * 一对多查询：获取生产商信息与关联的产品列表
     *
     * @author pan_junbiao
     */
    @Test
    @Transactional
    public void getFactoryAndProductList() {
        Factory factory = factoryRepository.findById(1).get();
        if (factory != null) {
            System.out.println("---------------1、生产商信息信息--------------");
            System.out.println("生产商编号：" + factory.getFactoryId());
            System.out.println("生产商名称：" + factory.getName());

            //获取关联的产品信息信息
            System.out.println("---------------2、产品信息信息---------------");
            List<Product> productList = factory.getProductList();
            if (productList != null && productList.size() > 0) {
                for (Product product : productList) {
                    System.out.println("产品编号：" + product.getId());
                    System.out.println("产品名称：" + product.getName());
                    System.out.println("产品价格：" + product.getPrice());
                    System.out.println("-------------------");
                }
            }
        }
    }


    /**
     * 多对一查询：获取产品信息与关联的生产商信息
     *
     * @author pan_junbiao
     */
    @Test
    public void getProductAndFactory() {
        List<Product> productList = productRepository.findAll();
        if (productList != null && productList.size() > 0) {
            for (Product product : productList) {
                //获取产品信息
                System.out.println("产品编号：" + product.getId());
                System.out.println("产品名称：" + product.getName());
                System.out.println("产品价格：" + product.getPrice());

                //获取关联的生产商信息信息
                Factory factory = product.getFactory();
                System.out.println("生产商编号：" + factory.getFactoryId());
                System.out.println("生产商名称：" + factory.getName());
                System.out.println("-------------------");
            }
        }
    }


    /**
     * 多对多查询：获取用户信息与关联的权限列表
     *
     * @author pan_junbiao
     */
    @Test
    @Transactional
    public void getUserAndRole() {
        List<M2MUser> userList = userRepository.findAll();
        if (userList != null && userList.size() > 0) {
            //遍历用户列表
            for (M2MUser user : userList) {
                System.out.println("用户编号：" + user.getId());
                System.out.println("用户名称：" + user.getName());

                //获取权限列表
                List<M2MRole> roleList = user.getRoleList();
                if (roleList != null && roleList.size() > 0) {
                    System.out.print("用户拥有的权限：");
                    for (M2MRole role : roleList) {
                        System.out.print(role.getRoleName() + ";");
                    }
                }
                System.out.println("\n-----------------------------------------------");
            }
        }
    }


}

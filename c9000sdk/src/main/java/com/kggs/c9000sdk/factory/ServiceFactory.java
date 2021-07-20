package com.kggs.c9000sdk.factory;


import com.kggs.c9000sdk.annotations.ServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 模拟注入的服务工厂
 *
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 13:40
 */
public class ServiceFactory {

    /**
     * 容器
     */
    private static Map<String, Object> containers = new HashMap<String, Object>();

    public static <T> T GetService(Class<T> interfaceClass) {
        //
        String _package = "com.kggs.c9000sdk.service.impl";
        //扫描指定package下是.class文件
        ClasspathPackageScanner scanner = new ClasspathPackageScanner();
        try {
            List<Class> classList = scanner.FindClass(_package);
            T _install = Find(classList, interfaceClass);
            return _install;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static <T> T Find(List<Class> classList, Class interfaceClass) throws IllegalAccessException, InstantiationException {
        for (Class classZ : classList) {
            if (Present(interfaceClass, classZ)) {
                return (T) classZ.newInstance();
            }
        }
        return null;
    }

    private static boolean Present(Class interfaceClass, Class targetClass) {
        //TODO 如果有必要，在检测目标类所实现的接口是否属于被声明接口
        Class<?>[] parentInterfaceClasses = targetClass.getInterfaces();
        //检测该类是否包含ServiceImpl注解
        if (targetClass.isAnnotationPresent(ServiceImpl.class)) {
            ServiceImpl serviceAnnotation = (ServiceImpl) targetClass.getAnnotation(ServiceImpl.class);
            return serviceAnnotation.classz().equals(interfaceClass);
        }
        return false;
    }


}

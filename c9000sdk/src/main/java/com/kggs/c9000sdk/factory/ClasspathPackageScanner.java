package com.kggs.c9000sdk.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : fjc.dane@gmail.com
 * @createtime : 2021/7/19 14:46
 */
public class ClasspathPackageScanner {

    private Logger logger = LoggerFactory.getLogger(ClasspathPackageScanner.class);
    private ClassLoader cl;

    public ClasspathPackageScanner() {
        this.cl = getClass().getClassLoader();
    }


    public List<Class> FindClass(String packageName) throws ClassNotFoundException, IOException {
        List<Class> clazzs = new ArrayList<Class>();
        return this.FindClass(packageName, clazzs);
    }

    public List<Class> FindClass(String packageName, List<Class> clazzs) throws ClassNotFoundException {
        String fileName = packageName.replaceAll("\\.", "/");
        URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
        File file = new File(url.getFile());
        File[] files = file.listFiles();
        for (File f : files) {
            //如果是目录，这进一个寻找
            if (f.isDirectory()) {
                //截取路径最后的文件夹名
                String currentPathName = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf(File.separator) + 1);
                //进一步寻找
                FindClass(packageName + "." + currentPathName, clazzs);
            } else {
                //如果是class文件
                if (f.getName().endsWith(".class")) {
                    //反射出实例
                    Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + "." + f.getName().replace(".class", ""));
                    clazzs.add(clazz);
                }
            }
        }
        return clazzs;
    }
}

package com.zc58s.springbootproxy.demo;

/*
 * springboot-proxy
 * com.zc58s.springbootproxy.demo
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/28 15:58
 */
public class Program {
    public static void main(String[] args) throws ClassNotFoundException {
        //这样子，嘿嘿，这个static方法是会执行的。
        Class c = Class.forName(Simple.class.getName());
        //这样子是不执行的。
        //c = Simple.class;
    }
}

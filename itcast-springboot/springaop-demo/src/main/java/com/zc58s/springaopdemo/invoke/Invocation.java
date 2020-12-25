package com.zc58s.springaopdemo.invoke;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * springaop-demo
 * com.zc58s.springaopdemo.invoke
 *
 * @author      : fjc.dane@gmail.com
 * @create time : 2020/12/25 14:37
 */
public class Invocation {
    private Object[] params;
    private Method method;
    private Object target;

    public Invocation(Object target, Method method, Object[] params) {
        this.params = params;
        this.method = method;
        this.target = target;
    }

    public Object proceed() throws InvocationTargetException, IllegalAccessException {
        return this.method.invoke(target, params);
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}

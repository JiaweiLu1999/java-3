package com.example.homework.day13.aop.interceptor;

import com.example.homework.day13.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterReturnMethodInterceptor implements MethodInterceptor{
    private Object aspectObj;
    private Method aspectMethod;

    public AfterReturnMethodInterceptor(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object res = mi.proceed();
        if (res != null && !(res instanceof Throwable)) {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectObj);
        }
        return res;
    }
}

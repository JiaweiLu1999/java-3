package com.example.homework.day13.aop.interceptor;



import com.example.homework.day13.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterMethodInterceptor implements MethodInterceptor {

    private Object aspectObj;
    private Method aspectMethod;

    public AfterMethodInterceptor(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable{
        Object res = mi.proceed();
        if (res == null) {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectObj);
        }
        return res;
    }
}

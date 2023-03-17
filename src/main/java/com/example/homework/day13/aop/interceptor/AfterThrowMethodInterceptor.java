package com.example.homework.day13.aop.interceptor;

import com.example.homework.day13.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterThrowMethodInterceptor implements MethodInterceptor{
    private Object aspectObj;
    private Method aspectMethod;

    public AfterThrowMethodInterceptor(Object aspectObj, Method aspectMethod) {
        this.aspectObj = aspectObj;
        this.aspectMethod = aspectMethod;
    }

    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        try {
            return mi.proceed();
        } catch (Throwable throwable) {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectObj);
            return throwable;
        }

    }
}

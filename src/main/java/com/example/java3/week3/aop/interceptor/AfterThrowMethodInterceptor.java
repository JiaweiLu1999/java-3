package com.example.java3.week3.aop.interceptor;

import com.example.java3.week3.aop.MethodInvocation;

import java.lang.reflect.Method;

public class AfterThrowMethodInterceptor  implements MethodInterceptor {

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
        } catch (Throwable ex) {
            aspectMethod.setAccessible(true);
            aspectMethod.invoke(aspectObj);
            throw ex;
        }
    }
}

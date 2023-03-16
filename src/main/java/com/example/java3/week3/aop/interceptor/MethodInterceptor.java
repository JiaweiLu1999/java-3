package com.example.java3.week3.aop.interceptor;


import com.example.java3.week3.aop.MethodInvocation;

public interface MethodInterceptor {
    Object invoke(MethodInvocation mi) throws Throwable;
}

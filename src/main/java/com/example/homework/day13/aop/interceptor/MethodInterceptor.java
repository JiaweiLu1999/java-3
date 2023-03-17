package com.example.homework.day13.aop.interceptor;


import com.example.homework.day13.aop.MethodInvocation;

public interface MethodInterceptor {
    Object invoke(MethodInvocation mi) throws Throwable;
}

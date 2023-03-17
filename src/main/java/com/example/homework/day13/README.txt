Day 13 solution documentation
@Jiawei Lu
-------------------------------------------------------------------------------
AOP

Content:
1. Create advice.AfterReturn annotation

2. Create advice.AfterThrow annotation

3. Modify interceptor.AfterMethodInterceptor
    if return is null, invoke aspectMethod

4. Create interceptor.AfterReturnMethodInterceptor
    if return is not null, invoke aspectMethod

5. Create interceptor.AfterReturnMethodInterceptor
    try-catch the mi.proceed()
    if there is a Throwable condition, return it
    else return proceed result





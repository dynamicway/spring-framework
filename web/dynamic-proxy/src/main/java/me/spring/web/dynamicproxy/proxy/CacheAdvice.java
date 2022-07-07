package me.spring.web.dynamicproxy.proxy;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.HashMap;
import java.util.Map;

public class CacheAdvice implements MethodInterceptor {

    private final Map<Arguments, Object> cacheRepository = new HashMap<>();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Arguments arguments = new Arguments(invocation.getArguments());
        return cacheRepository.computeIfAbsent(arguments, _key -> proceed(invocation));
    }

    private Object proceed(MethodInvocation invocation) {
        try {
            return invocation.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}


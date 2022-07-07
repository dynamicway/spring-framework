package me.spring.web.dynamicproxy.proxy;

import lombok.RequiredArgsConstructor;
import me.spring.web.dynamicproxy.util.Cached;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CacheJdkDynamicProxy implements InvocationHandler {

    private final Object target;

    private final Map<Arguments, Object> cacheRepository = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (method.getAnnotation(Cached.class) != null) {
            Arguments arguments = new Arguments(args);
            if (cacheRepository.containsKey(arguments))
                return cacheRepository.get(arguments);
            else {
                Object result = method.invoke(target, args);
                cacheRepository.put(arguments, result);
                return result;
            }
        }
        return method.invoke(target, args);
    }

}

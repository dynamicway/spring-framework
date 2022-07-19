package me.spring.web.dynamicproxy.proxy;

import lombok.RequiredArgsConstructor;
import me.spring.web.dynamicproxy.util.Cached;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class CacheCgLibProxy implements MethodInterceptor {

    private final Object target;

    private final Map<Arguments, Object> cacheRepository = new HashMap<>();

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        Cached cached = AnnotationUtils.findAnnotation(method, Cached.class);
        if (cached != null) {
            Arguments arguments = new Arguments(args);
            if (cacheRepository.containsKey(arguments))
                return cacheRepository.get(arguments);
            else {
                Object result = methodProxy.invoke(target, args);
                cacheRepository.put(arguments, result);
                return result;
            }
        }
        return methodProxy.invoke(target, args);
    }

}

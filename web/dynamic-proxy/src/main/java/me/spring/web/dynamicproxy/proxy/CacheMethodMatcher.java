package me.spring.web.dynamicproxy.proxy;

import me.spring.web.dynamicproxy.util.Cached;
import org.springframework.aop.MethodMatcher;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;

public class CacheMethodMatcher implements MethodMatcher {

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return AnnotationUtils.findAnnotation(method, Cached.class) != null;
    }

    @Override
    public boolean isRuntime() {
        return false;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass, Object... args) {
        throw new UnsupportedOperationException("cannot be runtime");
    }

}

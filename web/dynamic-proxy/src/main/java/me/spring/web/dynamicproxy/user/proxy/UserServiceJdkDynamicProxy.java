package me.spring.web.dynamicproxy.user.proxy;

import lombok.RequiredArgsConstructor;
import me.spring.web.dynamicproxy.util.Cached;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserServiceJdkDynamicProxy implements InvocationHandler {

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

    private record Arguments(Object[] args) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Arguments arguments = (Arguments) o;
            return Arrays.equals(args, arguments.args);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(args);
        }
    }

}

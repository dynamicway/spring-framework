package me.spring.web.dynamicproxy.user.proxy;

import lombok.RequiredArgsConstructor;
import me.spring.web.dynamicproxy.user.UserService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class UserServiceJdkDynamicProxy implements InvocationHandler {

    private final UserService target;

    private final Map<Long, String> cacheRepository = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        if (method.getName().equals("getUserName")) {
            return cacheRepository.computeIfAbsent((Long) args[0], target::getUserName);
        }
        return method.invoke(proxy, args);
    }

}

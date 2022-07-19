package me.spring.web.dynamicproxy.user;

import me.spring.web.dynamicproxy.util.Cached;

public interface UserService {

    @Cached
    String getUserName(long userId);

}

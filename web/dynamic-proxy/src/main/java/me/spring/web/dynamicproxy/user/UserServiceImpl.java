package me.spring.web.dynamicproxy.user;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String getUserName(long userId) {
        User user = userRepository.getUserById(userId);
        return user.name();
    }

}

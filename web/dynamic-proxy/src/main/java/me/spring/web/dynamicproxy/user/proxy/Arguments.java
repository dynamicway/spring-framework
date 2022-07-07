package me.spring.web.dynamicproxy.user.proxy;

import java.util.Arrays;

record Arguments(Object[] args) {
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

package com.itheima._09_generic_reflect;

import java.util.List;
import java.util.Map;

/**
 * 拿来当反射目标的"假仓储"。
 * 通过它的字段和方法签名，演示如何在运行期把"被擦除"的泛型实参再读回来。
 */
public class UserRepository {

    public List<String> names;

    public Map<Long, User> userMap;

    public List<User> findUsers(int page, int size) {
        return null;
    }

    public static class User {
        public final long id;
        public final String name;

        public User(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" + id + ", " + name + "}";
        }
    }
}

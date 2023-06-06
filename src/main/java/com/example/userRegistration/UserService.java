package com.example.userRegistration;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {
    private Map<Long, User> map = new HashMap<>();
    private AtomicLong counter = new AtomicLong();

    public User read(Long id) {
        return map.get(id);
    }

    public User create(User user) {
        long key = counter.incrementAndGet();
        user.setId(key);
        map.put(key, user);
        return user;
    }

    public User update(Long id, User user) {
        user.setId(id);
        User oldUser = map.replace(id, user);
        return oldUser == null ? null : user;
    }

    public void delete(Long id) {
        map.remove(id);
    }
}

package com.example.userRegistration;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    // map user ID to User objects
    Map<Long, User> map = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/user")
    public long user(@RequestParam(value = "email", defaultValue = "null") String email,
                     @RequestParam(value = "firstName", defaultValue = "null") String firstName,
                     @RequestParam(value = "lastName", defaultValue = "null") String lastName,
                     @RequestParam(value = "address", defaultValue = "null") String address,
                     @RequestParam(value = "password", defaultValue = "null") String password) {
        long ID = counter.incrementAndGet();
        User user =  new User(email, firstName, lastName, address, password);
        map.put(ID, user);
        return ID;
    }


//    public String getUserInfo(@RequestParam(value = "ID", defaultValue = "1") String ID) {
//        long longID = Long.parseLong(ID);
//        String template = "email = %s; firstName = %s; lastName = %s; address = %s; password = %s;";
//        User user = map.get(longID);
//        return String.format(template, user.email, user.firstName, user.lastName, user.address, user.password);
//    }
    @GetMapping("/getUserInfo")
    public User getUserInfo(@RequestParam(value = "ID", defaultValue = "1") String ID) {
        long longID = Long.parseLong(ID);
        return map.get(longID);
    }
}

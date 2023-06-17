package com.example.userRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService2 {
    private UserRepository userRepository;

    @Autowired
    public UserService2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User read(Long id) {
        return userRepository.getById(id);
    }

    public User create(User user) {
        userRepository.save(user);
        return user;
    }

    public User update(Long id, User user) {
        User oldUser = read(id);
        if (oldUser == null) {
            throw new IllegalArgumentException("Not found");
        }
        oldUser.address = user.address;
        oldUser.email = user.email;
        oldUser.firstName = user.firstName;
        oldUser.lastName = user.lastName;

        userRepository.save(oldUser);
        return oldUser;
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}

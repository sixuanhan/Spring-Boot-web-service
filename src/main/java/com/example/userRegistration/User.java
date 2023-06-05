package com.example.userRegistration;

public class User {
    String email;
    String firstName;
    String lastName;
    String address;
    String password;

    public User(String email, String firstName, String lastName, String address, String password) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
    }
}

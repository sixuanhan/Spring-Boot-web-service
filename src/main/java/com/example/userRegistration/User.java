package com.example.userRegistration;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "email")
    String email;

    @Column(name = "firstName")
    String firstName;

    @Column(name = "lastName")
    String lastName;

    @Column(name = "address")
    String address;

    @Column(name = "password")
    String password;

    @Column(name = "country")
    String country;

    @Column(name = "population")
    long population;

    public User(){}

    public User(String email, String firstName, String lastName, String address, String password, String country) {
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.country = country;
    }

    public User(Long id, String email, String firstName, String lastName, String address, String password, String country, long population) {
        super();
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.password = password;
        this.country = country;
        this.population = population;
    }

    public Long getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public long getPopulation() {
        return population;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", country='" + country + '\'' +
                ", population='" + population + '\'' +
                '}';
    }
}

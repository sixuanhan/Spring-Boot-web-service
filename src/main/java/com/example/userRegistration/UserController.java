package com.example.userRegistration;

import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/students")
public class UserController {
    @Autowired
    private UserService service;


//    @GetMapping("/user")
//    public long user(@RequestParam(value = "email", defaultValue = "null") String email,
//                     @RequestParam(value = "firstName", defaultValue = "null") String firstName,
//                     @RequestParam(value = "lastName", defaultValue = "null") String lastName,
//                     @RequestParam(value = "address", defaultValue = "null") String address,
//                     @RequestParam(value = "password", defaultValue = "null") String password) {
//        long ID = counter.incrementAndGet();
//        User user =  new User(email, firstName, lastName, address, password);
//        map.put(ID, user);
//        return ID;
//    }

    @PostMapping("/")
    public ResponseEntity<User> create(@RequestBody User user) throws URISyntaxException {
        User createdUser = service.create(user);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(createdUser);
    }



//    public String getUserInfo(@RequestParam(value = "ID", defaultValue = "1") String ID) {
//        long longID = Long.parseLong(ID);
//        String template = "email = %s; firstName = %s; lastName = %s; address = %s; password = %s;";
//        User user = map.get(longID);
//        return String.format(template, user.email, user.firstName, user.lastName, user.address, user.password);
//    }

//    @GetMapping("/getUserInfo")
//    public User getUserInfo(@RequestParam(value = "ID", defaultValue = "1") String ID) {
//        long longID = Long.parseLong(ID);
//        return map.get(longID);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<User> read(@PathVariable("id") Long id) {
        User foundUser = service.read(id);
        if (foundUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundUser);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        User updatedUser = service.update(id, user);
        if (updatedUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedUser);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.noContent().build();
    }
}

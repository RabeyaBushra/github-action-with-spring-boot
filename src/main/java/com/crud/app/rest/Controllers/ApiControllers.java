package com.crud.app.rest.Controllers;

import com.crud.app.rest.Models.User;
import com.crud.app.rest.Repo.UserRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ApiControllers {
    @Autowired
    private UserRepo userRepo;

    @GetMapping(value = "/")
    public String getPage() {
        return "welcome to our project";
    }

    @GetMapping(value = "/users")
    public List<User> getUsers() {

        return userRepo.findAll();
    }


    @GetMapping(value = "/user/{id}")
    public ResponseEntity<?> getSingleUser(@PathVariable long id) {
        if (userRepo.findById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userRepo.findById((id)));
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No User found for this id");
        }
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> saveUser(@RequestBody @NotNull User user) {
        if (userRepo.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user.getEmail() + " already exist");
        } else {
            userRepo.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("save the new info");
        }


    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
        if (userRepo.findById(id).isPresent()) {

            User updateUser = userRepo.findById(id).get();
            updateUser.setEmail(user.getEmail());
            updateUser.setFirst_Name(user.getFirst_Name());
            updateUser.setLast_Name(user.getLast_Name());
            updateUser.setOccupation(user.getOccupation());
            updateUser.setAge(user.getAge());
            userRepo.save(updateUser);
            return ResponseEntity.status(HttpStatus.OK).body("Updatde");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No User found for this id");
        }
    }
}

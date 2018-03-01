package io.saagie.ddd.legacytodomain.controllers;

import io.saagie.ddd.legacytodomain.dao.UserAlreadyExistsException;
import io.saagie.ddd.legacytodomain.model.User;
import io.saagie.ddd.legacytodomain.services.TooManyPlatformWritersException;
import io.saagie.ddd.legacytodomain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<User>> allUsers() {
        return ResponseEntity.ok(this.userService.listAllUsers());
    }

    @PostMapping("")
    public ResponseEntity<Void> register(@RequestBody UserRegistrationDto registration) {
        try {
            this.userService.register(registration);
            return ResponseEntity
                    .created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                    .build();
        } catch (TooManyPlatformWritersException | UserAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}

package io.saagie.ddd.legacytodomain.controllers;

import io.saagie.ddd.legacytodomain.model.User;
import io.saagie.ddd.legacytodomain.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/platforms")
public class PlatformController {
    private UserService userService;

    @Autowired
    public PlatformController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}/users")
    public ResponseEntity<List<User>> allPlatformUsers(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(this.userService.listPlatformUsers(id));
    }
}

package io.saagie.ddd.legacytodomain.controllers;

import io.saagie.ddd.legacytodomain.model.User;
import io.saagie.ddd.legacytodomain.services.AuthorizationLimitReachedException;
import io.saagie.ddd.legacytodomain.services.TooManyPlatformWritersException;
import io.saagie.ddd.legacytodomain.services.UserNotFoundException;
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

    @PostMapping("/{id}/roles")
    public ResponseEntity<Void> definePlatformRole(@PathVariable("id") Integer id, @RequestBody PlatformRoleDto platformRole) {
        try {
            this.userService.definePlatformRole(id, platformRole);
            return ResponseEntity
                    .created(ServletUriComponentsBuilder.fromCurrentRequest().build().toUri())
                    .build();
        } catch (TooManyPlatformWritersException | AuthorizationLimitReachedException e) {
            return ResponseEntity.badRequest().build();
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

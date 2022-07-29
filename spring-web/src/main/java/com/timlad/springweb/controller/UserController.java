package com.timlad.springweb.controller;

import com.timlad.springweb.entities.User;
import com.timlad.springweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/all_users")
    public List<User> findAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/super_admin")
    // @PreAuthorize("hasRole('ADMIN')")
    public String adminPage() {
        return "hello_super_admin";
    }

    @GetMapping("/user_info")
    public String daoTestPage(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new RuntimeException("Unable to find user by username: " + principal.getName()));
        return "Authenticated user info: " + user.getUsername() + " : " + user.getEmail() + " : " + user.getRoles();
    }
}

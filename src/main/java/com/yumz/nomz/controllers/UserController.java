package com.yumz.nomz.controllers;

import com.yumz.nomz.models.User;
import com.yumz.nomz.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value="/create",method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseBody
    public ResponseEntity createUser(@RequestBody User user) {
        Map<String, Object> responseData = new HashMap<>();
        try {
            userService.createUser(user);
            responseData.put("success", true);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.put("success", false);
            responseData.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(responseData);
        }
    }
}

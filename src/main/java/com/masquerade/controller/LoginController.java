package com.masquerade.controller;

import com.masquerade.model.User;
import com.masquerade.service.UserService;
import com.masquerade.tools.controller.Section;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = Section.CrossOriginUrl)
@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody User user) {
        // Check if the provided credentials are valid
        if (userService.checkCredentials(user.getUsername(), user.getHashedPassword())) {
            // Generate a JWT token for the user
            // Return the JWT token in the response
            return ResponseEntity.ok(userService.login(user));
        } else {
            // Return an unauthorized status code if the credentials are invalid
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Void> signup(@RequestBody User user) {
        // Check if the provided username is already taken
        if (userService.isUsernameTaken(user.getUsername())) {
            // Return a conflict status code if the username is already taken
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } else {
            // Save the new user to the user store
            userService.saveUser(user);
            // Return a success status code
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
    }

    @RequestMapping(value = "/change-password", method = RequestMethod.POST)
    public ResponseEntity<Void> changePassword(@RequestBody User user) {
        // Save the new user to the user store
        userService.update(user);
        // Return a success status code
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(value = "/change-username", method = RequestMethod.POST)
    public ResponseEntity<Void> changeUsername(@RequestBody User user) {
        // Save the new user to the user store
        userService.update(user);
        // Return a success status code
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
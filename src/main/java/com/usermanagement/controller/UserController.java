package com.usermanagement.controller;

import com.usermanagement.model.User;
import com.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for User management operations
 */
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService userService;
    
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * Create a new user
     * @param user The user to create
     * @return ResponseEntity with the created user
     */
    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        try {
            User createdUser = userService.createUser(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * Get all users
     * @return ResponseEntity with list of all users
     */
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Get user by ID
     * @param id The ID to search for
     * @return ResponseEntity with the user if found
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> {
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "User not found with id: " + id);
                    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
                });
    }
    
    /**
     * Update an existing user
     * @param id The ID of the user to update
     * @param userDetails The updated user details
     * @return ResponseEntity with the updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @Valid @RequestBody User userDetails) {
        try {
            User updatedUser = userService.updateUser(id, userDetails);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }
    
    /**
     * Delete a user
     * @param id The ID of the user to delete
     * @return ResponseEntity with success message
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "User deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
        }
    }
    
    /**
     * Search users by email
     * @param email The email to search for
     * @return ResponseEntity with the user if found
     */
    @GetMapping("/search/email")
    public ResponseEntity<?> searchUserByEmail(@RequestParam String email) {
        return userService.getUserByEmail(email)
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> {
                    Map<String, String> errorResponse = new HashMap<>();
                    errorResponse.put("error", "User not found with email: " + email);
                    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
                });
    }
    
    /**
     * Search users by name
     * @param name The name to search for
     * @return ResponseEntity with list of users
     */
    @GetMapping("/search/name")
    public ResponseEntity<List<User>> searchUsersByName(@RequestParam String name) {
        List<User> users = userService.searchUsersByName(name);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Search users by surname
     * @param surname The surname to search for
     * @return ResponseEntity with list of users
     */
    @GetMapping("/search/surname")
    public ResponseEntity<List<User>> searchUsersBySurname(@RequestParam String surname) {
        List<User> users = userService.searchUsersBySurname(surname);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
    
    /**
     * Search users by nationality
     * @param nationality The nationality to search for
     * @return ResponseEntity with list of users
     */
    @GetMapping("/search/nationality")
    public ResponseEntity<List<User>> searchUsersByNationality(@RequestParam String nationality) {
        List<User> users = userService.searchUsersByNationality(nationality);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
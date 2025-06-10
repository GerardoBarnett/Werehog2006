package com.usermanagement.service;

import com.usermanagement.model.User;

import java.util.List;
import java.util.Optional;

/**
 * Service interface for User management operations
 */
public interface UserService {
    
    /**
     * Create a new user
     * @param user The user to create
     * @return The created user
     */
    User createUser(User user);
    
    /**
     * Get all users
     * @return List of all users
     */
    List<User> getAllUsers();
    
    /**
     * Get user by ID
     * @param id The ID to search for
     * @return Optional containing the user if found
     */
    Optional<User> getUserById(Long id);
    
    /**
     * Get user by email
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    Optional<User> getUserByEmail(String email);
    
    /**
     * Update an existing user
     * @param id The ID of the user to update
     * @param userDetails The updated user details
     * @return The updated user
     */
    User updateUser(Long id, User userDetails);
    
    /**
     * Delete a user
     * @param id The ID of the user to delete
     */
    void deleteUser(Long id);
    
    /**
     * Search users by name
     * @param name The name to search for
     * @return List of users with the given name
     */
    List<User> searchUsersByName(String name);
    
    /**
     * Search users by surname
     * @param surname The surname to search for
     * @return List of users with the given surname
     */
    List<User> searchUsersBySurname(String surname);
    
    /**
     * Search users by nationality
     * @param nationality The nationality to search for
     * @return List of users with the given nationality
     */
    List<User> searchUsersByNationality(String nationality);
}
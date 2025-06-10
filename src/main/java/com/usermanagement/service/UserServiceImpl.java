package com.usermanagement.service;

import com.usermanagement.model.User;
import com.usermanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of UserService interface
 */
@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * Create a new user
     * @param user The user to create
     * @return The created user
     * @throws IllegalArgumentException if email already exists
     */
    @Override
    public User createUser(User user) {
        // Check if email already exists
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + user.getEmail());
        }
        
        return userRepository.save(user);
    }
    
    /**
     * Get all users
     * @return List of all users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * Get user by ID
     * @param id The ID to search for
     * @return Optional containing the user if found
     */
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * Get user by email
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * Update an existing user
     * @param id The ID of the user to update
     * @param userDetails The updated user details
     * @return The updated user
     * @throws EntityNotFoundException if user not found
     * @throws IllegalArgumentException if email already exists
     */
    @Override
    public User updateUser(Long id, User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        
        // Check if email is being changed and if it already exists
        if (!user.getEmail().equals(userDetails.getEmail()) && 
                userRepository.existsByEmail(userDetails.getEmail())) {
            throw new IllegalArgumentException("Email already exists: " + userDetails.getEmail());
        }
        
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setEmail(userDetails.getEmail());
        user.setNationality(userDetails.getNationality());
        
        return userRepository.save(user);
    }
    
    /**
     * Delete a user
     * @param id The ID of the user to delete
     * @throws EntityNotFoundException if user not found
     */
    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    /**
     * Search users by name
     * @param name The name to search for
     * @return List of users with the given name
     */
    @Override
    public List<User> searchUsersByName(String name) {
        return userRepository.findByName(name);
    }
    
    /**
     * Search users by surname
     * @param surname The surname to search for
     * @return List of users with the given surname
     */
    @Override
    public List<User> searchUsersBySurname(String surname) {
        return userRepository.findBySurname(surname);
    }
    
    /**
     * Search users by nationality
     * @param nationality The nationality to search for
     * @return List of users with the given nationality
     */
    @Override
    public List<User> searchUsersByNationality(String nationality) {
        return userRepository.findByNationality(nationality);
    }
}
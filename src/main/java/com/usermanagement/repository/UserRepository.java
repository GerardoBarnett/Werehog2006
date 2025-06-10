package com.usermanagement.repository;

import com.usermanagement.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity
 * Provides CRUD operations for User objects
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * Find a user by email
     * @param email The email to search for
     * @return Optional containing the user if found
     */
    Optional<User> findByEmail(String email);
    
    /**
     * Check if a user with the given email exists
     * @param email The email to check
     * @return true if a user with the email exists, false otherwise
     */
    boolean existsByEmail(String email);
    
    /**
     * Find users by name
     * @param name The name to search for
     * @return List of users with the given name
     */
    java.util.List<User> findByName(String name);
    
    /**
     * Find users by surname
     * @param surname The surname to search for
     * @return List of users with the given surname
     */
    java.util.List<User> findBySurname(String surname);
    
    /**
     * Find users by nationality
     * @param nationality The nationality to search for
     * @return List of users with the given nationality
     */
    java.util.List<User> findByNationality(String nationality);
}
package com.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Represents a User entity with personal information
 */
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank(message = "Name is required")
    private String name;
    
    @NotBlank(message = "Surname is required")
    private String surname;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    
    @NotBlank(message = "Nationality is required")
    private String nationality;
    
    // Default constructor required by JPA
    public User() {
    }
    
    /**
     * Constructs a new User with the specified details
     * @param id Unique identifier for the user
     * @param name User's first name
     * @param surname User's last name
     * @param email User's email address
     * @param nationality User's nationality
     */
    public User(Long id, String name, String surname, String email, String nationality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.nationality = nationality;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getNationality() {
        return nationality;
    }
    
    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
    
    /**
     * Provides a string representation of the User object
     * @return Formatted string containing all user details
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
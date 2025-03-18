import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a User entity with basic personal information
 */
class User {
    private int id;
    private String name;
    private String surname;
    private String email;

    /**
     * Constructs a new User with the specified details
     * @param id Unique identifier for the user
     * @param name User's first name
     * @param surname User's last name
     * @param email User's email address
     */
    public User(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getters and Setters with self-documenting names
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }

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
                '}';
    }
}

/**
 * Manages user operations including creation, search, update, and deletion
 */
class UserManagementSystem {
    private List<User> users;
    private Scanner scanner;
    private int nextId;

    /**
     * Initializes the UserManagementSystem with empty user list and scanner
     */
    public UserManagementSystem() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }

    /**
     * Starts the management system and handles the main program loop
     * Processes user input and manages exceptions
     */
    public void start() {
        boolean running = true;
        while (running) {
            try {
                displayMenu();
                int choice = getIntInput("Enter your choice (1-5): ");

                switch (choice) {
                    case 1:
                        createUser();
                        break;
                    case 2:
                        searchUser();
                        break;
                    case 3:
                        updateUser();
                        break;
                    case 4:
                        deleteUser();
                        break;
                    case 5:
                        running = false;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid choice! Please select between 1 and 5.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                scanner.nextLine(); // Clear scanner buffer
            }
        }
    }

    /**
     * Displays the main menu options to the user
     */
    private void displayMenu() {
        System.out.println("\nUser Management System");
        System.out.println("1. Create User");
        System.out.println("2. Search User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Exit");
    }

    /**
     * Creates a new user with provided details and validates input
     * @throws IllegalArgumentException if email is invalid or already exists
     */
    private void createUser() throws IllegalArgumentException {
        System.out.println("\nCreating new user:");
        String name = getStringInput("Enter name: ");
        String surname = getStringInput("Enter surname: ");
        String email = getStringInput("Enter email: ");

        // Validate email format
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email format!");
        }

        // Check if email already exists
        if (users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email))) {
            throw new IllegalArgumentException("Email already exists!");
        }

        User newUser = new User(nextId++, name, surname, email);
        users.add(newUser);
        System.out.println("User created successfully! User ID: " + newUser.getId());
    }

    /**
     * Searches for a user by ID or email
     * @throws IllegalArgumentException if no users exist or if user is not found
     */
    private void searchUser() throws IllegalArgumentException {
        if (users.isEmpty()) {
            throw new IllegalArgumentException("No users in the system!");
        }

        System.out.println("\nSearch user by:");
        System.out.println("1. ID");
        System.out.println("2. Email");
        
        int searchChoice = getIntInput("Enter your choice (1-2): ");
        
        switch (searchChoice) {
            case 1:
                int id = getIntInput("Enter user ID: ");
                User userById = findUserById(id);
                System.out.println("User found: " + userById);
                break;
            case 2:
                String email = getStringInput("Enter user email: ");
                User userByEmail = findUserByEmail(email);
                System.out.println("User found: " + userByEmail);
                break;
            default:
                throw new IllegalArgumentException("Invalid search choice!");
        }
    }

    /**
     * Updates existing user information
     * @throws IllegalArgumentException if user is not found or if new email is invalid
     */
    private void updateUser() throws IllegalArgumentException {
        // ... (rest of the code remains the same)
    }

    /**
     * Deletes a user from the system
     * @throws IllegalArgumentException if user is not found
     */
    private void deleteUser() throws IllegalArgumentException {
        int id = getIntInput("Enter user ID to delete: ");
        User user = findUserById(id);
        users.remove(user);
        System.out.println("User deleted successfully!");
    }

    /**
     * Finds a user by their ID
     * @param id The ID to search for
     * @return The found user
     * @throws IllegalArgumentException if user is not found
     */
    private User findUserById(int id) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }


    /**
     * Finds a user by their name
     * @param email The name to search for
     * @return The found user
     * @throws IllegalArgumentException if user is not found
     */
    private User findUserByName(string name) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getName() == name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with name: " + name));
    }

    /**
     * Finds a user by their email
     * @param email The email to search for
     * @return The found user
     * @throws IllegalArgumentException if user is not found
     */
    private User findUserByEmail(String email) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    /**
     * Gets string input from user with validation
     * @param prompt The message to display to user
     * @return The validated input string
     * @throws IllegalArgumentException if input is empty
     */
    private String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty!");
        }
        return input;
    }

    /**
     * Gets optional string input from user
     * @param prompt The message to display to user
     * @return The input string, may be empty
     */
    private String getStringInputOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    /**
     * Gets integer input from user with validation
     * @param prompt The message to display to user
     * @return The validated integer input
     * @throws IllegalArgumentException if input is not a valid number
     */
    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number!");
        }
    }

    /**
     * Validates email format using basic pattern matching
     * @param email The email to validate
     * @return true if email format is valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}


public class UserManagementApp {
    public static void main(String[] args) {
        UserManagementSystem system = new UserManagementSystem();
        system.start();
    }
}

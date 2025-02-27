import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class User {
    private int id;
    private String name;
    private String surname;
    private String email;

    public User(int id, String name, String surname, String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setEmail(String email) { this.email = email; }

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

class UserManagementSystem {
    private List<User> users;
    private Scanner scanner;
    private int nextId;

    public UserManagementSystem() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
    }

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

    private void displayMenu() {
        System.out.println("\nUser Management System");
        System.out.println("1. Create User");
        System.out.println("2. Search User");
        System.out.println("3. Update User");
        System.out.println("4. Delete User");
        System.out.println("5. Exit");
    }

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

    private void updateUser() throws IllegalArgumentException {
        int id = getIntInput("Enter user ID to update: ");
        User user = findUserById(id);

        System.out.println("Current user details: " + user);
        System.out.println("\nEnter new details (press Enter to keep current value):");

        String name = getStringInputOptional("Enter new name: ");
        if (!name.isEmpty()) user.setName(name);

        String surname = getStringInputOptional("Enter new surname: ");
        if (!surname.isEmpty()) user.setSurname(surname);

        String email = getStringInputOptional("Enter new email: ");
        if (!email.isEmpty()) {
            if (!isValidEmail(email)) {
                throw new IllegalArgumentException("Invalid email format!");
            }
            if (users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email) && u.getId() != id)) {
                throw new IllegalArgumentException("Email already exists!");
            }
            user.setEmail(email);
        }

        System.out.println("User updated successfully!");
    }

    private void deleteUser() throws IllegalArgumentException {
        int id = getIntInput("Enter user ID to delete: ");
        User user = findUserById(id);
        users.remove(user);
        System.out.println("User deleted successfully!");
    }

    private User findUserById(int id) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    private User findUserByEmail(String email) throws IllegalArgumentException {
        return users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("User not found with email: " + email));
    }

    private String getStringInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.nextLine().trim();
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty!");
        }
        return input;
    }

    private String getStringInputOptional(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Please enter a valid number!");
        }
    }

    private boolean isValidEmail(String email) {
        // Basic email validation
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }
}

public class UserManagementApp {
    public static void main(String[] args) {
        UserManagementSystem system = new UserManagementSystem();
        system.start();
    }
}

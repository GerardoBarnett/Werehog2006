package com.usermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.usermanagement.controller.UserController;
import com.usermanagement.model.User;
import com.usermanagement.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateUser() throws Exception {
        User user = new User(null, "John", "Doe", "john.doe@example.com", "American");
        User savedUser = new User(1L, "John", "Doe", "john.doe@example.com", "American");
        
        when(userService.createUser(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John")))
                .andExpect(jsonPath("$.surname", is("Doe")))
                .andExpect(jsonPath("$.email", is("john.doe@example.com")))
                .andExpect(jsonPath("$.nationality", is("American")));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        User user1 = new User(1L, "John", "Doe", "john.doe@example.com", "American");
        User user2 = new User(2L, "Jane", "Smith", "jane.smith@example.com", "British");
        
        when(userService.getAllUsers()).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[1].name", is("Jane")));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "American");
        
        when(userService.getUserById(1L)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John")));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User userDetails = new User(null, "John", "Updated", "john.updated@example.com", "Canadian");
        User updatedUser = new User(1L, "John", "Updated", "john.updated@example.com", "Canadian");
        
        when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);

        mockMvc.perform(put("/api/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDetails)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.surname", is("Updated")))
                .andExpect(jsonPath("$.nationality", is("Canadian")));
    }

    @Test
    public void testDeleteUser() throws Exception {
        doNothing().when(userService).deleteUser(1L);

        mockMvc.perform(delete("/api/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message", is("User deleted successfully")));
    }

    @Test
    public void testSearchUsersByName() throws Exception {
        User user1 = new User(1L, "John", "Doe", "john.doe@example.com", "American");
        User user2 = new User(3L, "John", "Smith", "john.smith@example.com", "Canadian");
        
        when(userService.searchUsersByName("John")).thenReturn(Arrays.asList(user1, user2));

        mockMvc.perform(get("/api/users/search/name").param("name", "John"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("John")))
                .andExpect(jsonPath("$[1].name", is("John")));
    }
}
package com.example.demo.user;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private Role role;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
    }
}

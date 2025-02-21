package com.example.demo.user;

import lombok.Data;

@Data
public class UserDTO {
    private Integer id;

    private String email;

    private String username;

    private String firstName;

    private String lastName;

    private Role role;

    private String avatar;

    private Boolean isVerified;

    public UserDTO(User user) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.role = user.getRole();
        this.avatar = user.getAvatar();
        this.isVerified = user.getIsVerified();
    }
}

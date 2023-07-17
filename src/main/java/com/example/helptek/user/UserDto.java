package com.example.helptek.user;

import com.example.helptek.role.Role;
import com.example.helptek.village.Village;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String mobile;
    private Village village;
    private Set<Role> roles;

    public UserDto(){

    }

    public UserDto(Long id, String firstName, String lastName, String username, String email, String mobile, Village village, Set<Role> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
        this.village = village;
        this.roles = roles;
    }
}

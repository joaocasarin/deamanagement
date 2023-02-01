package com.dea.management.app.user.dto;

import com.dea.management.app.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserDto {

    private String name;
    private String email;
    private String linkedin;

    public static List<UserDto> fromUsers(List<User> users) {
        return users.stream().map(user -> {
            UserDto userDto = UserDto.fromUser(user);
            return userDto;
        }).collect(Collectors.toList());
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setLinkedin(user.getLinkedin());

        return userDto;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}

package com.dea.management.app.user.controller;

import com.dea.management.app.user.domain.User;
import com.dea.management.app.user.dto.UserDto;
import com.dea.management.app.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/users")
    public Page<UserDto> getAllUsers(@RequestParam Optional<Integer> page, @RequestParam Optional<Integer> pageSize) {
        Integer newPage = page.orElse(0);
        Integer newPageSize = pageSize.orElse(1);

        Page<User> users = this.userService.findAllUsers(newPage, newPageSize);

        return users.map(user -> UserDto.fromUser(user));
    }
}

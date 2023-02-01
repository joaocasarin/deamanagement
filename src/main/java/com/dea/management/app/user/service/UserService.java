package com.dea.management.app.user.service;

import com.dea.management.app.exceptions.NotFoundException;
import com.dea.management.app.user.domain.User;
import com.dea.management.app.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> findAllUsers(Integer page, Integer pageSize) {
        return this.userRepository.findAllUsers(PageRequest.of(page, pageSize));
    }

    public User findUserByEmail(String email) {
        Optional<User> user = this.userRepository.findByEmail(email);

        if (user.isPresent()) {
            return user.get();
        }

        throw new NotFoundException(User.class, email);
    }
}

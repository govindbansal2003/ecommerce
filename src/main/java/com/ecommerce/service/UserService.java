package com.ecommerce.service;

import com.ecommerce.Entity.User;
import com.ecommerce.exception.UserException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findUserById(Long userId) throws UserException;
    User findUserProfileByJwt(String jwt) throws UserException;
    List<User> findAllUsers();
}

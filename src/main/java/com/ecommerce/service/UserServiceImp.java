package com.ecommerce.service;

import com.ecommerce.Entity.User;
import com.ecommerce.config.JwtTokenProvider;
import com.ecommerce.exception.UserException;
import com.ecommerce.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService{
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    public UserServiceImp(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent())
            return user.get();
        throw new UserException("User not found with this id"+userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
       String email = jwtTokenProvider.getEmailFromJwtToken(jwt);
       User user = userRepository.findByEmail(email);
       if(user == null)throw new  UserException("User not found with this email"+email);
       return user;
    }

    @Override
    public List<User> findAllUsers() {
        // TODO Auto-generated method stub
        return userRepository.findAllByOrderByCreatedAtDesc();
    }
}

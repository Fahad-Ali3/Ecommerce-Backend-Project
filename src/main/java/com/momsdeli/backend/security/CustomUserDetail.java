package com.momsdeli.backend.security;


import com.momsdeli.backend.exceptions.ResourceNotFoundException;
import com.momsdeli.backend.model.User;
import com.momsdeli.backend.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetail implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User",username,"User-Username"));
        return user;
    }
}

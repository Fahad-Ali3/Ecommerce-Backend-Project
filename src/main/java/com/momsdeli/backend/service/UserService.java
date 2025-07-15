package com.momsdeli.backend.service;

import com.momsdeli.backend.dto.UserDTO;
import com.momsdeli.backend.model.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);
    UserDTO deleteUser(Long userId);
    Page<UserDTO> getAllUsers(Integer pageSize, Integer size, String sortBy);
    UserDTO getUserById(Long userId);
    UserDTO updateUser(Long userId, UserDTO userDTO);
    UserDTO registerUser(UserDTO userDTO);
}

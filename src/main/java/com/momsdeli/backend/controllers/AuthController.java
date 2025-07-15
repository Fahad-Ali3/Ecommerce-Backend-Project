package com.momsdeli.backend.controllers;


import com.momsdeli.backend.dto.UserDTO;
import com.momsdeli.backend.model.JwtRequest;
import com.momsdeli.backend.model.JwtResponse;
import com.momsdeli.backend.security.JwtHelper;
import com.momsdeli.backend.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/auth")
@RestController()
@Tag(name = "Authorization")
public class AuthController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserService userServ;

    @Autowired
    private AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@Valid @RequestBody JwtRequest request){
        this.authenticate(request.getUsername(),request.getPassword());

        UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());

        String token=jwtHelper.generateToken(userDetails);
        JwtResponse response=new JwtResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) {
        // Register the user
        UserDTO registeredUser = userService.registerUser(userDTO);

        // Return the registered user details
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }


    private void authenticate(String username,String password){
        UsernamePasswordAuthenticationToken usernamePasswordToken=new UsernamePasswordAuthenticationToken(username,password);
        try {
            this.authenticationManager.authenticate(usernamePasswordToken);
        }catch (DisabledException e){
            throw new DisabledException("User is disabled...");
        }

    }

}

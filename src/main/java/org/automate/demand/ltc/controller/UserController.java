package org.automate.demand.ltc.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.micrometer.common.lang.NonNull;
import org.automate.demand.ltc.dtos.*;
import org.automate.demand.ltc.exceptions.InvalidEmailOrPasswordException;
import org.automate.demand.ltc.exceptions.TokenNotExistsOrAlreadyExpiredException;
import org.automate.demand.ltc.exceptions.UserEmailAlreadyExistsException;
import org.automate.demand.ltc.exceptions.UserNotFoundException;
import org.automate.demand.ltc.service.impl.JwtService;
import org.automate.demand.ltc.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserServiceImpl userServiceImpl;


    @Autowired
    public UserController(UserServiceImpl userServiceImpl, JwtService jwtService) {

        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers(){
        return ResponseEntity.ok(userServiceImpl.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getSingleUser(@PathVariable Long id) throws UserNotFoundException {
        return  ResponseEntity.ok(userServiceImpl.getSingleUser(id));
    }

    @PostMapping()
    public ResponseEntity<UserDto> addNewUser(@RequestBody UserDto userDto){
        return ResponseEntity.ok(userServiceImpl.addNewUser(userDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> replaceUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.replaceUser(id, userDto));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) throws UserNotFoundException {
        return ResponseEntity.ok(userServiceImpl.updateUser(id, userDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userServiceImpl.deleteUser(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException {
        return ResponseEntity.ok(userServiceImpl.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException {
        userServiceImpl.signUp(signUpRequestDto);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException {
        userServiceImpl.logout(logoutRequestDto);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @PostMapping("/validate/{token}")
    public UserDto validateToken(@PathVariable("token") @NonNull String token) {
        return UserDto.from(userServiceImpl.validateToken(token));
    }


}

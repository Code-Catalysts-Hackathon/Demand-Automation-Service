package org.automate.demand.ltc.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.automate.demand.ltc.dtos.*;
import org.automate.demand.ltc.exceptions.InvalidEmailOrPasswordException;
import org.automate.demand.ltc.exceptions.TokenNotExistsOrAlreadyExpiredException;
import org.automate.demand.ltc.exceptions.UserEmailAlreadyExistsException;
import org.automate.demand.ltc.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<UserDto> getAllUsers();

    UserDto getSingleUser(Long id) throws UserNotFoundException;

    UserDto addNewUser(UserDto userDto);

    UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException;

    UserDto replaceUser(Long id, UserDto userDto) throws UserNotFoundException;

    LoginResponseDto login(LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException;

    void signUp(SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException;

    void logout(LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException;

    void deleteUser(Long id);

}

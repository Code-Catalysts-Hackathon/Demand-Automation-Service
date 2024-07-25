package org.automate.demand.ltc.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.RandomStringUtils;
import org.automate.demand.ltc.dtos.*;
import org.automate.demand.ltc.exceptions.InvalidEmailOrPasswordException;
import org.automate.demand.ltc.exceptions.TokenNotExistsOrAlreadyExpiredException;
import org.automate.demand.ltc.exceptions.UserEmailAlreadyExistsException;
import org.automate.demand.ltc.exceptions.UserNotFoundException;
import org.automate.demand.ltc.mapper.UserMapper;
import org.automate.demand.ltc.entity.Token;
import org.automate.demand.ltc.entity.User;
import org.automate.demand.ltc.repository.TokenRepository;
import org.automate.demand.ltc.repository.UserRepository;
import org.automate.demand.ltc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final UserMapper userMapper;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, TokenRepository tokenRepository, UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }


    public List<UserDto> getAllUsers(){
        List<User> userDetails = userRepository.findAll();
        return userMapper.UserToUserDto(userDetails);
    }

    @Override
    public UserDto getSingleUser(Long id) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);

        if(optionalUserDetails.isEmpty()){
            throw new UserNotFoundException("User Not found ");
        }
        return userMapper.userToUserDto(optionalUserDetails.get()) ;
    }

    @Override
    public UserDto addNewUser(UserDto userDto) {
        User user = userMapper.userDtoToUser(userDto);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userToUserDto(userRepository.save(userMapper.UpdateUserFromDto(userDto, optionalUserDetails.get())));
    }

    @Override
    public UserDto replaceUser(Long id, UserDto userDto) throws UserNotFoundException {
        Optional<User> optionalUserDetails = userRepository.findById(id);
        if(optionalUserDetails.isEmpty()){
            throw  new UserNotFoundException("The User does not exist with id " + id);
        }
        return userMapper.userToUserDto(userRepository.save(
                userMapper.userDtoToUser(userDto)
        ));
    }

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequestDto) throws InvalidEmailOrPasswordException, JsonProcessingException {
        Optional<User> optionalUser = userRepository.findByEmail(loginRequestDto.getEmail());
        if(optionalUser.isEmpty()
        || !Objects.equals(optionalUser.get().getEmail(), loginRequestDto.getEmail())){
            throw new InvalidEmailOrPasswordException("Invalid Email");
        }

        if (!bCryptPasswordEncoder.matches(loginRequestDto.getPassword(), optionalUser.get().getHashedPassword())) {
            // throw password not matching exception
            throw new InvalidEmailOrPasswordException("Invalid Password");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDto.getEmail(),
                        loginRequestDto.getPassword()
                )
        );
         String jwtToken = jwtService.generateToken(optionalUser.get());
        LoginResponseDto loginResponse = new LoginResponseDto();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return loginResponse;

     }

    @Override
    public void signUp(SignUpRequestDto signUpRequestDto) throws UserEmailAlreadyExistsException {
        Optional<User> optionalUser = userRepository.findByEmail(signUpRequestDto.getEmail());
        if(optionalUser.isPresent()){
            throw new UserEmailAlreadyExistsException("Email already taken ");
        }
        signUpRequestDto.setPassword(bCryptPasswordEncoder.encode(signUpRequestDto.getPassword()));
        User user = new User();
        user.setName(signUpRequestDto.getName());
        user.setEmail(signUpRequestDto.getEmail());
        user.setHashedPassword(signUpRequestDto.getPassword());
        userRepository.save(user);
    }

    @Override
    public void logout(LogoutRequestDto logoutRequestDto) throws TokenNotExistsOrAlreadyExpiredException {
        Optional<Token> optionalToken = tokenRepository.findByValueAndDeleted(logoutRequestDto.getToken(), false);
        if (optionalToken.isEmpty()){
            throw new TokenNotExistsOrAlreadyExpiredException("Token does not exist or invalid token");
        }
        Token token = optionalToken.get();
       // token.setDeleted(true);
        tokenRepository.save(token);
    }

    public User validateToken(String token) {
        Optional<Token> tkn = tokenRepository.
                findByValueAndDeletedEqualsAndExpiryAtGreaterThan(token, false, new Date());

        return tkn.map(Token::getUser).orElse(null);

        // TODO 2: Instead of validating via the DB, as the token is now a JWT
        // token, validate using JWT

    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}

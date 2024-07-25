package org.automate.demand.ltc.exceptions.handler;

import org.automate.demand.ltc.dtos.ErrorDto;
import org.automate.demand.ltc.exceptions.InvalidEmailOrPasswordException;
import org.automate.demand.ltc.exceptions.TokenNotExistsOrAlreadyExpiredException;
import org.automate.demand.ltc.exceptions.UserEmailAlreadyExistsException;
import org.automate.demand.ltc.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
@Slf4j
public class UserExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDto> handleUserNotFoundException(UserNotFoundException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);

    }

    @ExceptionHandler(UserEmailAlreadyExistsException.class)
    public ResponseEntity<ErrorDto> handleUserEmailAlreadyExistsException(UserEmailAlreadyExistsException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode(400)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }

    @ExceptionHandler(InvalidEmailOrPasswordException.class)
    public ResponseEntity<ErrorDto> handleInvalidEmailOrPasswordException(InvalidEmailOrPasswordException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

    @ExceptionHandler(TokenNotExistsOrAlreadyExpiredException.class)
    public  ResponseEntity<ErrorDto> handleInvalidTokenException(TokenNotExistsOrAlreadyExpiredException ex){
        log.warn(ex.getMessage());
        var errorDto = ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND)
                .errorCode(404)
                .errorMessage(ex.getMessage())
                .timeStamp(Instant.now())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDto);
    }

}

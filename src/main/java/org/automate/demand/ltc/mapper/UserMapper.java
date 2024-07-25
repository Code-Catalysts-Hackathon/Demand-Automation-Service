package org.automate.demand.ltc.mapper;


import org.automate.demand.ltc.dtos.SignUpRequestDto;
import org.automate.demand.ltc.dtos.UserDto;
import org.automate.demand.ltc.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(uses = JsonNullableMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = "spring")
public interface UserMapper {
List<UserDto> UserToUserDto(List<User> userDetails);

UserDto userToUserDto(User user);

User userDtoToUser(UserDto userDto);

User UpdateUserFromDto(UserDto userDto, @MappingTarget User user);

User SignUpRequestDtoToUser(SignUpRequestDto signUpRequestDto);



}

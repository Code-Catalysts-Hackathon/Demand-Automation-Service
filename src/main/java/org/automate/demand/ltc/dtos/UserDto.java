package org.automate.demand.ltc.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.ManyToMany;
import org.automate.demand.ltc.entity.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class UserDto extends BaseEntity {
    private String name;
    private String email;
    @ManyToMany
    private UserRoleEntity roles;
    private boolean isEmailVerified;

    public static UserDto from(User user) {
        if (user == null) return null;

        UserDto userDto = new UserDto();
        userDto.email = user.getEmail();
        userDto.name = user.getName();
        userDto.roles = user.getUserRole();
        userDto.isEmailVerified = user.isEmailVerified();

        return userDto;
    }

}

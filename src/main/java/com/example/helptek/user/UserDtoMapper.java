package com.example.helptek.user;

import com.example.helptek.village.Village;
import com.example.helptek.village.VillageDto;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDtoMapper  implements Function<User, UserDto> {
    @Override
    public UserDto apply(User user) {
        return new UserDto(user.getId(), user.getFirstname(),user.getLastname(), user.getUsername(), user.getEmail(), user.getMobile(), user.getVillage(),user.getRoles());
    }

    public UserDto tupleToUserDto(Tuple tuple) {
        UserDto userDto = new UserDto();
        userDto.setId(tuple.get("id", Long.class));
        userDto.setEmail(tuple.get("email", String.class));
        userDto.setFirstName(tuple.get("first_name", String.class));
        userDto.setLastName(tuple.get("last_name", String.class));
        userDto.setUsername(tuple.get("userName", String.class));
        userDto.setMobile(tuple.get("mobile", String.class));
        userDto.setVillage(tuple.get("villages", Village.class));
        return userDto;
    }
}

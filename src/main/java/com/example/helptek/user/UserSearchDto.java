package com.example.helptek.user;

import lombok.Data;

@Data
public class UserSearchDto extends UserDto {
    private int pageSize;
    private int pageNumber;
    private Long villageId;

}

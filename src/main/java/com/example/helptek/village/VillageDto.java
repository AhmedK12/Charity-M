package com.example.helptek.village;

import com.example.helptek.user.User;
import lombok.Data;

@Data
public class VillageDto {
    private Long id;
    private String name;
    private String district;
    private String pinCode;
    private User representative;


    public VillageDto(Long id, String name, String district, String pinCode, User representative) {
        this.id = id;
        this.name = name;
        this.district = district;
        this.pinCode = pinCode;
        this.representative = representative;
    }

    public VillageDto() {

    }
}

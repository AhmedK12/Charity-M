package com.example.helptek.village;

import com.example.helptek.user.User;
import jakarta.persistence.Tuple;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.function.Function;

@Component
public class VillageDtoMapper implements Function<Village, VillageDto> {
    @Override
    public VillageDto apply(Village village) {
        return new VillageDto(village.getId(),village.getName(),village.getDistrict(),village.getPinCode(),village.getRepresentative());
    }

    public VillageDto tupleToVillageDto(Tuple tuple){
        VillageDto villageDto = new VillageDto();
        villageDto.setId(tuple.get("id",Long.class));
        villageDto.setName(tuple.get("name",String.class));
        villageDto.setDistrict(tuple.get("district",String.class));
        villageDto.setPinCode(tuple.get("pin_code",String.class));
        return villageDto;
    }

}

package com.example.helptek.village;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class VillageSearchDto extends VillageDto{
    private int pageSize = 200;
    private int pageNumber=1;
    private String representativeId;


}

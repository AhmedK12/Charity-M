package com.example.helptek.village;

import com.example.helptek.common.PageSortResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface VillageService {

    public VillageDto create(Village village);
    public void delete(Long villageId);

    public PageSortResponse<List<VillageDto>> search(VillageSearchDto villageSearchDto) throws NoSuchFieldException;

    VillageDto update(Village village);
}

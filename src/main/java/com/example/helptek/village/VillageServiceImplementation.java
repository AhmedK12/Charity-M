package com.example.helptek.village;

import com.example.helptek.common.PageSortResponse;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class VillageServiceImplementation implements VillageService {


    private final VillageRepository villageRepository;
    private final VillageDtoMapper villageDtoMapper;
    private final VillageNativeRepository villageNativeRepository;
    @Autowired
    public VillageServiceImplementation(VillageRepository villageRepository, VillageDtoMapper villageDtoMapper, VillageNativeRepository villageNativeRepository){
        this.villageRepository = villageRepository;
        this.villageDtoMapper = villageDtoMapper;
        this.villageNativeRepository = villageNativeRepository;
    }
    @Override
    @Transactional
    public VillageDto create(Village village) {
       return villageDtoMapper.apply(villageRepository.save(village));
    }

    @Override
    @Transactional
    public void delete(Long villageId) {
            villageRepository.deleteById(villageId);
    }

    @Override
    public PageSortResponse<List<VillageDto>> search(VillageSearchDto villageSearchDto) throws NoSuchFieldException {
        PageSortResponse<List<VillageDto>> response = new PageSortResponse<>();
        response.setPageNo(villageSearchDto.getPageNumber());
        villageNativeRepository.getSearchResult(response,villageSearchDto);
        return response;
    }

    @Override
    @Transactional
    public VillageDto update(Village village) {
           return villageDtoMapper.apply(villageRepository.save(village));
    }


}

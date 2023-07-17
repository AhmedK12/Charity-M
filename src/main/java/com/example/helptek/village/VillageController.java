package com.example.helptek.village;

import com.example.helptek.common.PageSortResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/v2/village")
public class VillageController {

    private final VillageService villageService;
    @Autowired
    public VillageController(VillageService villageService){
        this.villageService = villageService;
    }
    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public VillageDto create(@RequestBody Village village){
          return villageService.create(village);
    }

    @PostMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody Village village){

    }

    @GetMapping(value = "/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public PageSortResponse<List<VillageDto>> getAll() throws NoSuchFieldException {
        return villageService.search(new VillageSearchDto());
    }

    @PostMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public PageSortResponse<List<VillageDto>> search(@RequestBody VillageSearchDto villageSearchDto) throws NoSuchFieldException {
        return villageService.search(villageSearchDto);
    }

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public VillageDto update(@RequestBody Village village){
         return villageService.update(village);
    }

}

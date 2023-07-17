package com.example.helptek.user;

import com.example.helptek.common.PageSortResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v2/user")
public class UserController {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }
    @PostMapping(value = "/create",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto create(@RequestBody User user){
        return userService.create(user);
    }

    @PostMapping(value = "/delete",produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@RequestBody User user){

    }

    @GetMapping(value = "/get-all",produces = MediaType.APPLICATION_JSON_VALUE)
    public PageSortResponse<List<UserDto>> getAll() throws NoSuchFieldException {
        return userService.search(new UserSearchDto());
    }

    @PostMapping(value = "/search",produces = MediaType.APPLICATION_JSON_VALUE)
    public PageSortResponse<List<UserDto>> search(@RequestBody UserSearchDto userSearchDto) throws NoSuchFieldException {
        return userService.search(userSearchDto);
    }

    @PostMapping(value = "/update",produces = MediaType.APPLICATION_JSON_VALUE)
    public UserDto update(@RequestBody User user){
        return userService.update(user);
    }

}

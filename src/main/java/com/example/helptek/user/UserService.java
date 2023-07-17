package com.example.helptek.user;

import com.example.helptek.common.PageSortResponse;
import com.example.helptek.village.Village;
import com.example.helptek.village.VillageDto;
import com.example.helptek.village.VillageSearchDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public UserDto create(User user);
    public void delete(Long userId);

    public PageSortResponse<List<UserDto>> search(UserSearchDto userSearchDto) throws NoSuchFieldException;

    UserDto update(User user);
}

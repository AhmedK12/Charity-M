package com.example.helptek.user;

import com.example.helptek.common.PageSortResponse;
import com.example.helptek.user.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class UserServiceImplementation implements UserService{
    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final UserNativeRepository userNativeRepository;
    @Autowired
    public UserServiceImplementation(UserRepository userRepository, UserDtoMapper userDtoMapper, UserNativeRepository userNativeRepository){
        this.userRepository = userRepository;
        this.userDtoMapper = userDtoMapper;
        this.userNativeRepository = userNativeRepository;
    }
    @Override
    @Transactional
    public UserDto create(User user) {
        return userDtoMapper.apply(userRepository.save(user));
    }

    @Override
    @Transactional
    public void delete(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public PageSortResponse<List<UserDto>> search(UserSearchDto userSearchDto) throws NoSuchFieldException {
        PageSortResponse<List<UserDto>> response = new PageSortResponse<>();
        response.setPageNo(userSearchDto.getPageNumber());
        userNativeRepository.getSearchResult(response,userSearchDto);
        return response;
    }

    @Override
    @Transactional
    public UserDto update(User user) {
        return userDtoMapper.apply(userRepository.save(user));
    }

}

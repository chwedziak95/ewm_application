package com.kc6379.zarzadzaniemagazynem.service;

import com.kc6379.zarzadzaniemagazynem.dto.UserDto;
import com.kc6379.zarzadzaniemagazynem.exceptions.EwmAppException;
import com.kc6379.zarzadzaniemagazynem.mapper.UserMapper;
import com.kc6379.zarzadzaniemagazynem.model.User;
import com.kc6379.zarzadzaniemagazynem.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Transactional
    public UserDto getUser(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EwmAppException("Nie znaleziono użytkownika o adresie email: " + email));
        return userMapper.toDto(user);
    }

}

package com.project.travelAgency.service;

import com.project.travelAgency.dto.UserDto;
import com.project.travelAgency.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService { // security

    boolean save(UserDto userDTO); //принимает дтошку и сохраняем

    void save(User user);// для сохранения юзера к определенно корзине, когда будет добавлять туры в корзину

    List<UserDto> getAll();

    User findByName(String name);

}

package com.project.tvavelAgency.service;

import com.project.tvavelAgency.model.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {

    boolean save(UserDto userDTO);

}

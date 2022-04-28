//package com.project.tvavelAgency.service;
//
//import com.project.tvavelAgency.model.dto.UserDto;
//import com.project.tvavelAgency.model.entity.Role;
//import com.project.tvavelAgency.model.entity.User;
//import org.springframework.stereotype.Component;
//
//@Component
//public class UserConverter {
//
//
//    public User fromUserDtoToUser(UserDto userDto) {
//        User user = User.builder()
//                .name(userDto.getUsername())
//                .password(userDto.getPassword())
//                .email(userDto.getPassword())
//                .role(Role.CLIENT)
//                .build();
//        return user;
//    }
//
//    public UserDto fromUserToUserDto(User user) {
//        return UserDto.builder()
//                .email(user.getEmail())
//                .password(user.getPassword())
//                .username(user.getName())
//                .build();
//    }
//}

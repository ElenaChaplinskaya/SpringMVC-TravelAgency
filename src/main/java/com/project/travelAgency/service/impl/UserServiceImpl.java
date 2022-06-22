package com.project.travelAgency.service.impl;


import com.project.travelAgency.dto.UserDto;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.Role;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.UserRepository;
import com.project.travelAgency.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public boolean save(UserDto userDto) {
        if (!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
            throw new RuntimeException("Password is not equal");
        }
        User user = User.builder()
                .name(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .email(userDto.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    //находим всех пользователей в БД и маппим их в DTO, выводим список.
    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    public User getDiscount(String name) {
        User user = userRepository.findFirstByName(name);

        List<Order> orders = user.getOrders();
        BigDecimal newSum = orders.stream().map(Order::getSum).reduce(BigDecimal.valueOf(0), BigDecimal::add);

        if (newSum.compareTo(new BigDecimal(1000)) >= 0 && newSum.compareTo(new BigDecimal(2000)) <= 0) {
            user.setDiscount(BigDecimal.valueOf(5));
            if (newSum.compareTo(new BigDecimal(2000)) >= 0 && newSum.compareTo(new BigDecimal(3000)) <= 0) {
                user.setDiscount(BigDecimal.valueOf(10));
                if (newSum.compareTo(new BigDecimal(3000)) >= 0 && newSum.compareTo(new BigDecimal(4000)) <= 0) {
                    user.setDiscount(BigDecimal.valueOf(15));
                } else {
                    user.setDiscount(BigDecimal.valueOf(20));
                }
            }
        }

        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //ищем пользователя в БД
        User user = userRepository.findFirstByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + username); // если не нашли пользователя
        }

        List<GrantedAuthority> roles = new ArrayList<>(); //если нашли, создаем лист ролей
        roles.add(new SimpleGrantedAuthority(user.getRole().name())); // добавляем роль юзера в лист

        return new org.springframework.security.core.userdetails.User( //собираем СПРИНГОВСКОГО юзера, ЮЗЕР КОТОРОГО ПОНИМАЕТ USERdETAIL собираем его их данных полученных в бд
                user.getName(),
                user.getPassword(),
                roles);
    }

    //конвертация User из БД в UserDto
    private UserDto toDto(User user) {
        return UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }
}

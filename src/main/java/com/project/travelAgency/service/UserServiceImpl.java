package com.project.travelAgency.service;


import com.project.travelAgency.model.dto.UserDto;
import com.project.travelAgency.model.entity.Role;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
        if(!Objects.equals(userDto.getPassword(), userDto.getMatchingPassword())) {
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
    public List<UserDto> getAll(){
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public User findByName(String name) {
        return userRepository.findFirstByName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //ищем пользователя
        User user = userRepository.findFirstByName(username);
        if(user == null){
            throw new UsernameNotFoundException("User not found with name: " + username); // если не нашли пользователя
        }

        List<GrantedAuthority> roles = new ArrayList<>(); //если нашли, создаем лист ролей
        roles.add(new SimpleGrantedAuthority(user.getRole().name())); // добавляем роль юзера в лист

        return new org.springframework.security.core.userdetails.User( //получаем спринговского юзера,
                user.getName(),
                user.getPassword(),
                roles);
    }

    //конвертация User из БД в UserDto
    private UserDto toDto(User user){
        return UserDto.builder()
                .username(user.getName())
                .email(user.getEmail())
                .build();
    }
}

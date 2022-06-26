package com.project.travelAgency.service;

import com.project.travelAgency.dto.UserDto;
import com.project.travelAgency.model.entity.Order;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.UserRepository;
import com.project.travelAgency.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before All tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Before All tests");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Before each test");
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void checkFindByName() {

        String name = "user666";
        User expectedUser = User.builder().id(1).name(name).build();
        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);


        User actualUser = userService.findByName(name);


        Assertions.assertNotNull(actualUser);
        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    void checkSaveIncorrectPassword() {

        UserDto userDto = UserDto.builder()
                .password("password")
                .matchingPassword("another")
                .build();


        Assertions.assertThrows(RuntimeException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                userService.save(userDto);
            }
        });
    }

    @Test
    void checkSave() {

        UserDto userDto = UserDto.builder()
                .username("name")
                .email("email")
                .password("password")
                .matchingPassword("password")
                .build();

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");


        boolean result = userService.save(userDto);


        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }

    @Test
    void checkDiscount() {
        String name = "user666";
        List<Order> orderList = new ArrayList<>();
        Order order = com.project.travelAgency.model.entity.Order.builder().sum(BigDecimal.valueOf(1000)).build();
        orderList.add(0, order);
        User expectedUser = User.builder().id(1).name(name).orders(orderList).build();
        Mockito.when(userRepository.findFirstByName(Mockito.anyString())).thenReturn(expectedUser);

        userService.getDiscount(expectedUser.getName());
        Assertions.assertEquals(new BigDecimal(15), expectedUser.getDiscount());


    }
}



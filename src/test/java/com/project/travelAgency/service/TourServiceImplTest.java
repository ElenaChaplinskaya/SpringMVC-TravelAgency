package com.project.travelAgency.service;

import com.project.travelAgency.dto.CountryDto;
import com.project.travelAgency.dto.TourDto;
import com.project.travelAgency.model.entity.Status;
import com.project.travelAgency.model.entity.TypeOfTour;
import com.project.travelAgency.model.repository.TourRepository;
import com.project.travelAgency.model.repository.UserRepository;
import com.project.travelAgency.service.impl.CartServiceImpl;
import com.project.travelAgency.service.impl.TourServiceImpl;
import com.project.travelAgency.service.impl.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;

public class TourServiceImplTest {

    private TourRepository tourRepository;
    private UserRepository userRepository;
    private TourServiceImpl tourService;
    private CartServiceImpl cartService;
    private UserServiceImpl userService;

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
        userRepository = Mockito.mock(UserRepository.class);
        tourRepository = Mockito.mock(TourRepository.class);
        tourService = new TourServiceImpl(tourRepository, userService, cartService);

    }

    @Test
    void checkSave() {

        CountryDto countryDto = new CountryDto();
        countryDto.setCountry("Italy");

        TourDto tourDto = TourDto.builder()
                .typeOfTour(TypeOfTour.Excursion)
                .countryDto(countryDto)
                .days(7)
                .price(BigDecimal.valueOf(100))
                .status(Status.HOT)
                .build();

        boolean result = tourService.save(tourDto);

        Assertions.assertTrue(result);
        Mockito.verify(tourRepository).save(Mockito.any());
    }

    @Test
    void checkGetById() {

        tourService.getById(2L);
        Mockito.verify(tourRepository, Mockito.times(1)).getById(ArgumentMatchers.eq(2L));
    }

}

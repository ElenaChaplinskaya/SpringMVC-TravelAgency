package com.project.travelAgency.service;

import com.project.travelAgency.model.dto.TourDto;
import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.mapper.TourMapper;
import com.project.travelAgency.model.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {


    private final TourMapper mapper = TourMapper.MAPPER;
    private final TourRepository tourRepository;
    private final UserService userService;
    private final CartService cartService;


    @Override
    public List<TourDto> getAll() {
        return mapper.fromTourList(tourRepository.findAll());
    }

    @Override
    public void addToUserCart(Integer tourId, String username) {

        User user = userService.findByName(username);
        if (user == null) {
            throw new RuntimeException("User " + username + " not found");
        }

        Cart cart = user.getCart();
        if (cart == null) {
            Cart newCart = cartService.createCart(user, Collections.singletonList(tourId));
            user.setCart(newCart);
            userService.save(user);
        } else {
            cartService.addTours(cart, Collections.singletonList(tourId));
        }
    }
}

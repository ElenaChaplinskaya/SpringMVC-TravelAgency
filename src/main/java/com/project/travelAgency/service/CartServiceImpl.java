package com.project.travelAgency.service;

import com.project.travelAgency.model.dto.CartDetailDto;
import com.project.travelAgency.model.dto.CartDto;
import com.project.travelAgency.model.dto.TourDto;
import com.project.travelAgency.model.entity.Cart;
import com.project.travelAgency.model.entity.Tour;
import com.project.travelAgency.model.entity.User;
import com.project.travelAgency.model.repository.CartRepository;
import com.project.travelAgency.model.repository.TourRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final TourRepository tourRepository;
    private final UserService userService;


    @Override
    public Cart createCart(User user, List<Long> tourIds) { //метод отработает, когда нужно будет добавлять туры в корзину
        Cart cart = new Cart(); //создает новую корзину
        cart.setUser(user); // сеттим юзера в корзину
        List<Tour> tourList = getCollectRefToursByIds(tourIds); //у нас есть список туров, который лежит в определенной корзине определенного пользователя

        return cartRepository.save(cart);
    }

    //getOne берет ссылку на объект, findById- берет сам объект
    private List<Tour> getCollectRefToursByIds(List<Long> tourIds) {
        return tourIds.stream()
                .map(tourRepository::getOne)
                .collect(Collectors.toList());
    }

    //туры мы берем по id
    @Override
    public void addTours(Cart cart, List<Long> tourIds) {
        List<Tour> tours = cart.getTours();
        List<Tour> newTourList = tours == null ? new ArrayList<>() : new ArrayList<>(tours);
        newTourList.addAll(getCollectRefToursByIds(tourIds));
        cart.setTours(newTourList);
        cartRepository.save(cart);

    }

    @Override
    public CartDto getCartByUser(String name) {
        User user = userService.findByName(name);
        if(user == null || user.getCart() == null){
            return new CartDto();
        }

        CartDto cartDto = new CartDto();
        Map<Long, CartDetailDto> mapByTourId = new HashMap<>();

        List<Tour> tours = user.getCart().getTours();
        for (Tour tour : tours) {
            CartDetailDto detail = mapByTourId.get(tour.getId());
            if(detail == null){
                mapByTourId.put(tour.getId(), new CartDetailDto(tour));
            }
            else {
                detail.setAmount(detail.getAmount().add(new BigDecimal(1.0)));
                detail.setSum(detail.getSum() + Double.valueOf(tour.getPrice().toString()));
            }
        }

        cartDto.setCartDetails(new ArrayList<>(mapByTourId.values()));
        cartDto.aggregate();

        return cartDto;
    }
}

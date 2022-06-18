package com.project.travelAgency.service.impl;

import com.project.travelAgency.model.entity.TypeOfTour;
import com.project.travelAgency.model.repository.TypeOfTourRepository;
import com.project.travelAgency.service.TypeOfTourService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TypeOfTourServiceImpl implements TypeOfTourService {

    private final TypeOfTourRepository typeOfTourRepository;
    @Override
    public List<TypeOfTour> getAll() {
        return typeOfTourRepository.findAll();
    }
}

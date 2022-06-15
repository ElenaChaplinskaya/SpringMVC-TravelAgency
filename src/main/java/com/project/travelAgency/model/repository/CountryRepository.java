package com.project.travelAgency.model.repository;

import com.project.travelAgency.model.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;

public interface CountryRepository extends JpaRepository<Country, Integer> {
}

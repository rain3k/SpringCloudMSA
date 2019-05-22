package io.spring.foodservice.repository;

import io.spring.foodservice.domain.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FoodRepository extends JpaRepository<Food, String> {
    List<Food> findAllBySpicylevelOrderById(Integer spicylevel);
}
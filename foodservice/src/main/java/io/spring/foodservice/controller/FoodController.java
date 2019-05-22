package io.spring.foodservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.spring.foodservice.domain.Food;
import io.spring.foodservice.repository.FoodRepository;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class FoodController {

    FoodRepository foodRepository;

    public FoodController(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @GetMapping("/food/{id}")
    public Food getFood(@PathVariable String id) {
        log.debug("Getting Food id : " + id);

        return foodRepository.getOne(id);
    }

    @GetMapping("/populate")
    public Integer populateFood() {
        List<Food> initialFood = new ArrayList<Food>() {{
            add(Food.builder().id("1").name("Kimchi Soup").price(10000).spicylevel(3).build());
            add(Food.builder().id("2").name("Seafood Pancake").price(10000).spicylevel(1).build());
            add(Food.builder().id("3").name("Pork Belly BBQ").price(10000).spicylevel(0).build());
            add(Food.builder().id("4").name("Chicken Soup with Abalone").price(10000).spicylevel(0).build());
            add(Food.builder().id("5").name("Bulgogi").price(10000).spicylevel(3).build());
            add(Food.builder().id("6").name("Rice with Mixed Vegitable").price(8000).spicylevel(2).build());
        }};
        return foodRepository.saveAll(initialFood).size();
    }

    @GetMapping("/food")
    public List<Food> getFood(@RequestParam("spicylevel") Integer spicylevel) {
        log.debug("Getting Food List by spicylevel : " + spicylevel);

        return foodRepository.findAllBySpicylevelOrderById(spicylevel);
    }

}
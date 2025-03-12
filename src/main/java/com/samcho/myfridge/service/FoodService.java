package com.samcho.myfridge.service;

import com.samcho.myfridge.model.Food;
import com.samcho.myfridge.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private FoodRepository foodRepository;

    /**
     * Constructs FoodService with given FoodRepository.
     * Used for dependency injection (DI) to wire FoodRepository.
     *
     * @param foodRepository foodRepository injected into FoodService
     */
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    /**
     * Finds all food items in the database.
     *
     * @return a list of food object in the database
     */
    public List<Food> getAllFood() {
        return (List<Food>) foodRepository.findAll();
    }

    /**
    * Adds new food item to the database.
    *
    * @param food food item to be added to the database
     */
    public void addFood(Food food) {
        foodRepository.save(food);
    }

    /**
     * Deletes food item by id from the database.
     *
     * @param id food id to be deleted from the database
     */
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public Optional<Food> findByID(Long id) {
        return foodRepository.findById(id);
    }

    public void updateFood(Food food) {
        foodRepository.save(food);
    }
}

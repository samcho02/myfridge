package com.samcho.myfridge.controller;

import com.samcho.myfridge.model.Food;
import com.samcho.myfridge.service.FoodService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/fridge-items")
public class FoodController {
    private FoodService foodService;

    /**
     * FoodController Constructor
     * Injects and wires foodService to FoodController.
     *
     * @param foodService foodservice injected into FoodController
     */
    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    /**
     * Handles GET requests to /fridge-items.
     * Retrieves all food items from the database and adds them to the model.
     *
     * @param model the model object used to pass data to the view
     * @return the name of the Thymeleaf template "food-list" to render the view
     */
    @GetMapping
    public String showFoodList(Model model) {
        List<Food> foodList = foodService.getAllFood();
        model.addAttribute("foodList", foodList);
        return "food-list";
    }

    /**
     * Handles GET requests to /fridge-items/add.
     * Prepares a new food object and adds it to the model for the form.
     *
     * @param model the model object used to pass data to the view
     * @return the name of the Thymeleaf template "add-food" to render the view
     */
    @GetMapping("/add")
    public String showAddFoodPage(Model model) {
        model.addAttribute("food", new Food());
        return "add-food";
    }

    /**
     * Handles POST requests to /fridge-items/add.
     * Saves the submitted food item to the database and redirects to the food list.
     *
     * @param food the food object submitted from the form
     * @return a redirect to /fridge-items after food item is added
     */
    @PostMapping("/add")
    public String addFood(@ModelAttribute("food") Food food){
        foodService.addFood(food);
        return "redirect:/fridge-items";
    }

    /**
     * Handles POST requests to /fridge-items/{id}/delete.
     * Because HTML Form doesn't support inherit DELETE, treat DELETE as POST.
     * Deletes user selected food object from the database
     * and redirects to the update food list.
     *
     * @param id food id selected by the user from the form
     * @return a redirect to /fridge-items after food item is deleted
     */
    @PostMapping("/{id}/delete")
    public String deleteFood(@PathVariable("id") Long id) {
        foodService.deleteFood(id);
        return "redirect:/fridge-items";
    }
}

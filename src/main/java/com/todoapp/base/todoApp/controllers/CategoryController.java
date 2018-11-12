package com.todoapp.base.todoApp.controllers;

import com.todoapp.base.todoApp.models.Category;
import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import com.todoapp.base.todoApp.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Autowired
    private ServiceUtils controllerUtils;

    @GetMapping("/category")
    public String getCategory(Model model){
        model.addAttribute("newCategory", new Category());
        TodoAppUser username = controllerUtils.getCurrentUser();
        model.addAttribute("categoryList", username.getCategories());
        return "category";
    }

    @PostMapping("/createNewCategory")
    public String saveCategory(Category newCategory, Model model){
        TodoAppUser username = controllerUtils.getCurrentUser();
        username.getCategories().add(newCategory);
        todoUserRepository.save(username);
        return "redirect:/category";
    }
}

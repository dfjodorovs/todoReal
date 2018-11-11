package com.todoapp.base.todoApp.controllers;

import com.todoapp.base.todoApp.models.Category;
import com.todoapp.base.todoApp.models.CategoryList;
import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.repositories.CategoryRepository;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CategoryController {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @GetMapping("/category")
    public String getCategory(Model model){
        model.addAttribute("newCategory", new Category());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TodoAppUser username = todoUserRepository.findByUsername(auth.getName());
        model.addAttribute("categoryList", username.getCategories());
        return "category";
    }

    @PostMapping("/createNewCategory")
    public String saveCategory(Category newCategory, Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        TodoAppUser username = todoUserRepository.findByUsername(auth.getName());
        username.getCategories().add(newCategory);
        todoUserRepository.save(username);
        return "redirect:/category";
    }
}

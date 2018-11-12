package com.todoapp.base.todoApp.controllers;

import com.todoapp.base.todoApp.models.CategoryList;
import com.todoapp.base.todoApp.models.TodoItem;
import com.todoapp.base.todoApp.services.ListService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoryListController {

    @Autowired
    private ListService listService;

    @GetMapping("/lists/{categoryName}")
    public String getList(
            @PathVariable(value = "categoryName") String categoryName,
            Model model) {

        List<CategoryList> categoryLists = listService.getAllCategoryLists(categoryName);

        model.addAttribute("currentCategory", categoryName);
        model.addAttribute("newList", new CategoryList());
        model.addAttribute("listOfCategoryLists", categoryLists);

        return "lists";
    }

    @PostMapping("/createNewList/{currentCategory}")
    public String saveCategory(
            CategoryList newCategoryList,
            @PathVariable(value = "currentCategory") String currentCategory,
            Model model){

        listService.saveCategoryList(newCategoryList, currentCategory);
        return "redirect:/lists/"+currentCategory;
    }

}

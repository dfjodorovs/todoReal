package com.todoapp.base.todoApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CategoryListController {

    @GetMapping("/lists/{categoryName}/{id}")
    public String getList(@PathVariable(value = "categoryName")String categoryName){
        System.out.println(categoryName);
        return "lists";
    }
}

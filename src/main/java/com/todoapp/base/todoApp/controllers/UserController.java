package com.todoapp.base.todoApp.controllers;

import com.todoapp.base.todoApp.models.Category;
import com.todoapp.base.todoApp.models.CategoryList;
import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.models.TodoItem;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @GetMapping("/")
    private String getNewUserPage(Model model){
        model.addAttribute("newUser",new TodoAppUser());
        return "signup";
    }

    @PostMapping("/")
    private String createNewUser(TodoAppUser user, Model model){
        todoUserRepository.save(user);
        model.addAttribute("saveOk","Saved!");
        model.addAttribute("newUser",new TodoAppUser());
        return "signup";
    }

//    @GetMapping("/test")
//    private TodoAppUser testMethod(){
//        TodoAppUser todoAppUser = new TodoAppUser();
//        todoAppUser.setEmail("some@email.com");
//        todoAppUser.setPassword("pass");
//        todoAppUser.setUsername("username");
//
//        List<Category> categories = new ArrayList<>();
//
//        Category category = new Category();
//        category.setName("Home");
//        categories.add(category);
//
//        List<CategoryList> categoryLists = new ArrayList<>();
//
//        CategoryList categoryList = new CategoryList();
//        categoryList.setName("Chores");
//        categoryLists.add(categoryList);
//
//        List<TodoItem> todoItems = new ArrayList<>();
//        TodoItem todoItem = new TodoItem();
//        todoItems.add(todoItem);
//
//        categoryList.setCategoryLists(todoItems);
//        category.setCategoryLists(categoryLists);
//        todoAppUser.setCategories(categories);
//
//        return todoUserRepository.save(todoAppUser);
//    }

}

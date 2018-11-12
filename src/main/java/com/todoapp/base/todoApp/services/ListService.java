package com.todoapp.base.todoApp.services;

import com.todoapp.base.todoApp.models.Category;
import com.todoapp.base.todoApp.models.CategoryList;
import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import com.todoapp.base.todoApp.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ListService {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Autowired
    private ServiceUtils controllerUtils;

    public CategoryList saveCategoryList(CategoryList newCategoryList, String categoty){
        TodoAppUser username = controllerUtils.getCurrentUser();
        Map<String, Category> userCategories  = username.getCategories().stream().collect(Collectors.toMap(Category::getName, category -> category));
        Category category = userCategories.get(categoty);
        category.getCategoryLists().add(newCategoryList);
        todoUserRepository.save(username);
        return newCategoryList;
    }

    public List<CategoryList> getAllCategoryLists(String categoryName){
        TodoAppUser currentUser = controllerUtils.getCurrentUser();
        Map<String, Category> userCategories  = currentUser.getCategories().stream().collect(Collectors.toMap(Category::getName, category -> category));
        return userCategories.get(categoryName).getCategoryLists();
    }
}

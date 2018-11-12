package com.todoapp.base.todoApp.services;

import com.todoapp.base.todoApp.models.Category;
import com.todoapp.base.todoApp.models.CategoryList;
import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.models.TodoItem;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import com.todoapp.base.todoApp.utils.ServiceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TodoItemService {

    @Autowired
    private TodoUserRepository todoUserRepository;

    @Autowired
    private ServiceUtils serviceUtils;

    public List<TodoItem> getTodoItems(String currentCategory, String listName){
        TodoAppUser username = serviceUtils.getCurrentUser();
        return getTodoItems(currentCategory, listName, username);
    }

    private List<TodoItem> getTodoItems(String currentCategory, String listName, TodoAppUser username) {
        CategoryList categoryList = getCategoryList(currentCategory, listName, username);
        return categoryList.getTodoItems();
    }

    private CategoryList getCategoryList(String currentCategory, String listName, TodoAppUser username) {
        Map<String, Category> userCategories  = username.getCategories().stream()
                .collect(Collectors.toMap(Category::getName, category -> category));
        Category category = userCategories.get(currentCategory);
        Map<String, CategoryList> stringCategoryListMap = category.getCategoryLists().stream()
                .collect(Collectors.toMap(CategoryList::getName, categoryList -> categoryList));
        return stringCategoryListMap.get(listName);
    }

    public void saveTodoItem(TodoItem todoItem, String currentCategory, String listName) {
        TodoAppUser username = serviceUtils.getCurrentUser();
        getTodoItems(currentCategory, listName, username).add(todoItem);
        todoUserRepository.save(username);
    }

    public void updateItems(List<TodoItem> todoItems, String currentCategory, String listName) {
        TodoAppUser username = serviceUtils.getCurrentUser();
        getCategoryList(currentCategory, listName, username).setTodoItems(todoItems);
        todoUserRepository.save(username);
    }

    public void setServiceUtils(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    public void setTodoUserRepository(TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
    }
}

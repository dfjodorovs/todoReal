package com.todoapp.base.todoApp.controllers;

import com.todoapp.base.todoApp.dao.TodoItemDao;
import com.todoapp.base.todoApp.models.TodoItem;
import com.todoapp.base.todoApp.services.TodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class TodoItemController {

    @Autowired
    private TodoItemService todoItemService;

    @GetMapping("/singleList/{currentCategory}/{listName}")
    public String getSingleList(
            @PathVariable(value = "currentCategory") String currentCategory,
            @PathVariable(value = "listName") String listName,
            Model model) {

        List<TodoItem> todoItems = todoItemService.getTodoItems(currentCategory, listName);

        model.addAttribute("listName", listName);
        model.addAttribute("categoryName", currentCategory);
        model.addAttribute("newTodoItem", new TodoItem());
        model.addAttribute("todoItemDAO", new TodoItemDao(todoItems));

        return "single_list";
    }

    @PostMapping("/createTodoItem/{currentCategory}/{listName}")
    public String createTodoItem(
            TodoItem todoItem,
            @PathVariable(value = "currentCategory") String currentCategory,
            @PathVariable(value = "listName") String listName,
            Model model) {
        todoItemService.saveTodoItem(todoItem, currentCategory, listName);
        return "redirect:/singleList/" + currentCategory + "/" + listName;
    }

    @PostMapping("/updateTodoItems/{currentCategory}/{listName}")
    public String updateTodoItems(
            TodoItemDao todoItems,
            @PathVariable(value = "currentCategory") String currentCategory,
            @PathVariable(value = "listName") String listName,
            Model model) {

        todoItemService.updateItems(todoItems.getTodoItems(), currentCategory, listName);
        return "redirect:/singleList/" + currentCategory + "/" + listName;
    }

    public void setTodoItemService(TodoItemService todoItemService) {
        this.todoItemService = todoItemService;
    }
}

package com.todoapp.base.todoApp.dao;

import com.todoapp.base.todoApp.models.TodoItem;

import java.util.ArrayList;
import java.util.List;

public class TodoItemDao {
    private List<TodoItem> todoItems;

    public TodoItemDao() {
        todoItems = new ArrayList<>();
    }

    public TodoItemDao(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }

    public List<TodoItem> getTodoItems() {
        return todoItems;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}

package com.todoapp.base.todoApp.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category_list")
public class CategoryList {

    @Id
    @GeneratedValue
    private long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TodoItem> todoItems= new ArrayList<>();


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TodoItem> getCategoryLists() {
        return todoItems;
    }

    public void setCategoryLists(List<TodoItem> todoItems) {
        this.todoItems = todoItems;
    }
}

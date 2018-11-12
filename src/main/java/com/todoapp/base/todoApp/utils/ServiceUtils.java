package com.todoapp.base.todoApp.utils;

import com.todoapp.base.todoApp.models.TodoAppUser;
import com.todoapp.base.todoApp.repositories.TodoUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ServiceUtils {

    @Autowired
    private TodoUserRepository todoUserRepository;

    public TodoAppUser getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return todoUserRepository.findByUsername(auth.getName());
    }

    public void setTodoUserRepository(TodoUserRepository todoUserRepository) {
        this.todoUserRepository = todoUserRepository;
    }
}

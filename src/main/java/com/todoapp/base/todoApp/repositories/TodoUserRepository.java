package com.todoapp.base.todoApp.repositories;

import com.todoapp.base.todoApp.models.TodoAppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoAppUser, Long> {

    TodoAppUser findByUsername(String username);
}

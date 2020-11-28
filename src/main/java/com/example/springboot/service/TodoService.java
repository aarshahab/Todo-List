package com.example.springboot.service;

import com.example.springboot.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    List<Todo> getAllTodo();
    Boolean checkDuplicate(String name);
    Todo save(Todo todo);
    Optional<Todo> find(Long id);
    void delete(Long id);
}

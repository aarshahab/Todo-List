package com.example.springboot.service.impl;

import com.example.springboot.model.Todo;
import com.example.springboot.repository.TodoRepository;
import com.example.springboot.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Primary
public class TodoServiceImpl implements TodoService {

    private final Logger log = LoggerFactory.getLogger(TodoServiceImpl.class);

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTodo(){
        return todoRepository.findAll();
    }

    @Override
    public Boolean checkDuplicate(String name){
        return todoRepository.checkDuplicate(name).isEmpty();
    }

    @Override
    public Todo save(Todo todo) {
        log.debug("Save todo : ", todo);
        return todoRepository.save(todo);
    }

    @Override
    public Optional<Todo> find(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        todoRepository.updateActive(false,id);
    }

}

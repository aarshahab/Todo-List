package com.example.springboot.controller;

import com.example.springboot.model.Tag;
import com.example.springboot.model.Todo;
import com.example.springboot.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/app")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public List<Todo> getTodos() {
        return todoService.getAllTodo();
    }

    @PostMapping("/todo")
    public ResponseEntity<?> saveTag(@RequestBody Todo todo) {
        if(!todoService.checkDuplicate(todo.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate todo name");
        }
        todoService.save(todo);
        return ResponseEntity.created(URI.create("")).body(todo);
    }

    @PutMapping("/todo/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable(value = "id") Long id,
                                         @Valid @RequestBody Todo todoDetail){

        Optional<Todo> todo = todoService.find(id);
        if (!todo.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todo id not found");
        if(!todoService.checkDuplicate(todoDetail.getName())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Duplicate todo name");
        }
        todoDetail.setId(id);
        Todo todoupdate = todoService.save(todoDetail);
        return ResponseEntity.ok().body(todoupdate);
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable(value = "id") Long id) {
        Optional<Todo> todo = todoService.find(id);
        if (!todo.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todo id not found");
        return ResponseEntity.ok().body(todo);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable(value = "id") Long id) {
        Optional<Todo> todo = todoService.find(id);
        if (!todo.isPresent())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Todo id not found");
        todoService.delete(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok().body(response);
    }
}

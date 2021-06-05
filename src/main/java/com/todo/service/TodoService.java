package com.todo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.model.TodoItem;
import com.todo.repository.TodoRepository;

@Service
public class TodoService {
    
    @Autowired
    TodoRepository repo;
    
    public List<TodoItem> getAllTodos() {
        return repo.findAll();
    }
    
    public TodoItem getTodoUsingId(int id) {
        return repo.findById(id).orElseThrow();
    }
    
    public TodoItem saveTodo(TodoItem todo) {
        return repo.save(todo);
    }
    
    public TodoItem updateTodo(TodoItem todo) {
        repo.findById(todo.getId()).orElseThrow();
        return repo.save(todo);
    }
    
    public TodoItem deleteTodo(int id) {
        TodoItem item = repo.findById(id).orElseThrow();
        repo.deleteById(id);
        return item;
    }
}

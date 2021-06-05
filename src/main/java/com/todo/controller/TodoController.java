package com.todo.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.NoSuchElementException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.model.TodoItem;
import com.todo.service.TodoService;

@RestController
@RequestMapping("/api/v1")
public class TodoController {
    
    @Autowired
    TodoService service;
    
    private static Logger log = LoggerFactory.getLogger(TodoController.class);
    
    @RequestMapping(value="/todo", method=RequestMethod.GET)
    public List<TodoItem> getAllTodos() {
        log.info(this.getClass() + ": Fetching all todos");
        return service.getAllTodos();
    }
    
    @RequestMapping(value="/todo/{id}", method=RequestMethod.GET)
    public ResponseEntity<TodoItem>  getTodoUsingId(@PathVariable int id) {
        log.info(this.getClass() + ": Fetching todo using Id: " + id);
        try {
            TodoItem item = service.getTodoUsingId(id);
            return ResponseEntity.ok().body(item);            
        } catch(NoSuchElementException e) {
            log.error(this.getClass() + ": Todo with id: {} doesn't exist", id);
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            log.error(this.getClass() + ": Bad Request!");
            return ResponseEntity.badRequest().build();
        }
    }
    
    @RequestMapping(value="/todo", method=RequestMethod.POST)
    public ResponseEntity<TodoItem> saveTodo(@RequestBody TodoItem todo) throws URISyntaxException {
        log.info(this.getClass() + ": Saving todo: {}", todo.getTodo());
        TodoItem item =  service.saveTodo(todo);
        return ResponseEntity.created(new URI("/api/v1/todo/" + item.getId())).body(item);
    }
    
    @RequestMapping(value="/todo", method=RequestMethod.PUT)
    public ResponseEntity<TodoItem> updateTodo(@RequestBody TodoItem todo) {
        log.info(this.getClass() + ": Updating todo with id: {}", todo.getId());
        try {
            TodoItem item = service.updateTodo(todo);
            return ResponseEntity.ok(item);
        } catch(NoSuchElementException e) {
            log.info(this.getClass() + ": Entity doesn't exist!");
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            log.error(this.getClass() + ": Some error occured! {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    @RequestMapping(value="/todo/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<TodoItem> deleteTodo(@PathVariable int id) {
        log.info(this.getClass() + ": Deleting todo with id: {}", id);
        
        try {
            TodoItem item = service.deleteTodo(id);
            return ResponseEntity.ok(item);
        } catch(NoSuchElementException e) {
            log.info(this.getClass() + ": Entity doesn't exist!");
            return ResponseEntity.notFound().build();
        } catch(Exception e) {
            log.error(this.getClass() + ": Some error occured! {}", e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
    
    
}

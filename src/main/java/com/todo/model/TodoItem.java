package com.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="todos")
public class TodoItem {
    
    @Id
    @GeneratedValue
    private int id;
    private String todo;
    private boolean isDone;
    
    public TodoItem(int id, String todo, boolean isDone) {
        super();
        this.id = id;
        this.todo = todo;
        this.isDone = isDone;
    }
    
    public TodoItem() {
        super();
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getTodo() {
        return todo;
    }
    
    public void setTodo(String todo) {
        this.todo = todo;
    }
    
    public boolean isDone() {
        return isDone;
    }
    
    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
    
    
}

package com.udemyspringboot.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> findByUsername(String username){
        return todoRepository.findAll();
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(1, username, description, targetDate, done);
        todoRepository.save(todo);
        return todo;
    }

    public void deleteTodoById(int id){
        todoRepository.deleteById(id);
    }

    public void updateTodoById(int id){

    }

    public Todo findTodoById(int id) {
        Todo todo = todoRepository.findById(id).get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todoRepository.save(todo);
    }
}

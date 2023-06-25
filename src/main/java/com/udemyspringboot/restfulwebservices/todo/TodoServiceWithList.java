package com.udemyspringboot.restfulwebservices.todo;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Service
public class TodoServiceWithList {
    private static List<Todo> todos = new ArrayList<>();
    private static int todosCount = 0;
    static {
        todos.add(new Todo(++todosCount, "vikas",
                "Learn Spring", LocalDate.now().plusMonths(1), false));
        todos.add(new Todo(++todosCount, "vikas",
                "Learn Selenium", LocalDate.now().plusMonths(2), false));
        todos.add(new Todo(++todosCount, "vikas",
                "Learn Database", LocalDate.now().plusMonths(5), false));
        todos.add(new Todo(++todosCount, "nick",
                "Learn DSA", LocalDate.now().plusMonths(3), false));
        todos.add(new Todo(++todosCount, "nick",
                "Learn Something new", LocalDate.now().plusMonths(3), false));
    }

    public List<Todo> findByUsername(String username){
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public Todo addTodo(String username, String description, LocalDate targetDate, boolean done){
        Todo todo = new Todo(++todosCount, username, description, targetDate, done);
        todos.add(todo);
        return todo;
    }

    public void deleteTodoById(int id){
        todos.removeIf(todo -> todo.getId() == id);
    }

    public void updateTodoById(int id){

    }

    public Todo findTodoById(int id) {
        Todo todo = todos.stream().filter(td -> td.getId() == id).findFirst().get();
        return todo;
    }

    public void updateTodo(Todo todo) {
        deleteTodoById(todo.getId());
        todos.add(todo);
    }
}

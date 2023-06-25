package com.udemyspringboot.restfulwebservices.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
RequestMethod.PUT})
public class TodoResourceJpa {
    @Autowired
    private TodoService todoService;
    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/users/{username}/todos")
    public List<Todo> getTodosByUsername(@PathVariable String username){

        return todoRepository.findByUsername(username);
    }

    @GetMapping("/users/{username}/todos/{id}")
    public Todo getTodoByUsernameAndId(@PathVariable String username, @PathVariable String id){
        Integer todoId = Integer.parseInt(id);
        return todoRepository.findById(todoId).get();
    }

    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodoByUsernameAndId(@PathVariable String id){
        Integer todoId = Integer.parseInt(id);
         todoRepository.deleteById(todoId);
         return ResponseEntity.noContent().build();
    }

    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodoByUsernameAndId(@PathVariable String username, @PathVariable String id,
                                                          @RequestBody Todo todo){
        todoRepository.save(todo);
        return todo;
    }

    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username, @RequestBody Todo todo){
        todo.setUsername(username);
        todo.setId(null);
        return todoRepository.save(todo);
//        Todo createdTodo = todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), todo.isDone() );

    }
}

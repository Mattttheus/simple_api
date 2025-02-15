package com.lucasangelo.todosimple.controllers;


import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;

import com.lucasangelo.todosimple.service.TaskService;


import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/task")
@Validated  

public class TaskCotroller{

 @Autowired
 private TaskService taskService;

@GetMapping("/{id}")

 public ResponseEntity<Task> fiEntity(@PathVariable Long id){
    
     Task task = taskService.findById(id);
     return ResponseEntity.ok().body(task);

 }
 @PutMapping
 @Validated
 public ResponseEntity<Void> create(@Valid @RequestBody Task obj){

    this.taskService.create(obj);
    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
    return ResponseEntity.created(uri).build();

 }
 @PutMapping ("/{id}")
 @Validated(User.UpdateUser.class)

public ResponseEntity<Void> update (@Valid @RequestBody Task obj, @PathVariable Long id) {

   obj.setId(id);
   this.taskService.update(obj);
   return ResponseEntity.noContent().build();
}

@DeleteMapping("/{id}")
public ResponseEntity<Void> delete (@Valid @RequestBody Task obj, @PathVariable Long id) {


    this.taskService.delete(id);
   return ResponseEntity.noContent().build();
}



@GetMapping("/user/{userId}")

public ResponseEntity<List<Task>> findAllByteUserId(@PathVariable Long userId) {

    List<Task> tasks = this.taskService.findAllByteUserId(userId);
    return ResponseEntity.ok().body(tasks);



} 



}
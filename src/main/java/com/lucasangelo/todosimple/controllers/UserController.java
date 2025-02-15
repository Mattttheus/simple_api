package com.lucasangelo.todosimple.controllers;


import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.models.User.CreateUser;
import com.lucasangelo.todosimple.service.UserService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
@Validated  

public class UserController {


  @Autowired
  private UserService userService;

  @GetMapping("/{id}")

     public ResponseEntity <User> findById (@PathVariable Long id) {
   
        User user = this.userService.FindByld(id);
        return ResponseEntity.ok().body(user);     

     }

     @Validated(CreateUser.class)
     @PostMapping
    
      public ResponseEntity<Void> create(@Valid @RequestBody User obj) {

        this.userService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    
      
      }

      @PutMapping ("/{id}")
      @Validated(User.UpdateUser.class)

    public ResponseEntity<Void> update (@Valid @RequestBody User obj, @PathVariable Long id) {
    
        obj.setId(id);
        this.userService.update(obj);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@Valid @RequestBody User obj, @PathVariable Long id) {


        this.userService.delete(id);
        return ResponseEntity.noContent().build();
    }
   
  
}
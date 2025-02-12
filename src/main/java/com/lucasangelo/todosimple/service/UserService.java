package com.lucasangelo.todosimple.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;
import com.lucasangelo.todosimple.repositories.UserRepository;

@Service

public class UserService {


 @Autowired

 private UserRepository userRepository;

 @Autowired

 private TaskRepository taskRepository; 

 public User FindByld(Long id) {

    Optional<User> user = this.userRepository.findById(id); 
    return user.orElseThrow(() -> new RuntimeException("User not found" + id + "Não esta cadastrado"));
 
  }
  
  
   @Transactional

     public User create(User obj) {
     obj.setId(null);
     obj = this.userRepository.save(obj); 
     this.taskRepository.saveAll(obj.getTask());
     return obj;

    }

    @Transactional

    public User update(User obj) {
     User newObj = FindByld(obj.getId());
     newObj.setPassword(obj.getPassword());
     return this.userRepository.save(newObj);

    }

    public void delete(Long id) {
     FindByld(id);
     
     try{

        this.userRepository.deleteById(id);
     }catch(Exception e) {

        throw new RuntimeException("User not found" + id + "Não esta cadastrado");
     }


    }


}

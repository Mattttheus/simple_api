package com.lucasangelo.todosimple.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucasangelo.todosimple.models.Task;
import com.lucasangelo.todosimple.models.User;
import com.lucasangelo.todosimple.repositories.TaskRepository;

import jakarta.transaction.Transactional;

@Service

public class TaskService {
 
    @Autowired
     private TaskRepository taskRepository;

     @Autowired 
     private UserService userService;


     public Task findById(Long id) {
        
         Optional <Task> task = this.taskRepository.findById(id);

         return task.orElseThrow(() -> new RuntimeException("Tarefa not found"));

}

      public List <Task> findAllByteUserId(Long userId) {

        List <Task> tasks = this.taskRepository.findByUser_Id(userId);
        return tasks;
      }

   @Transactional

    public Task create(Task obj) {
        
        User user = this.userService.FindByld(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = this.taskRepository.save(obj);
        return obj;

    }
    @Transactional

    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        return this.taskRepository.save(newObj);

    }
    @Transactional

    public void delete(Long id) {
        findById(id);
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel deletar a tarefa");
        }
    }
}
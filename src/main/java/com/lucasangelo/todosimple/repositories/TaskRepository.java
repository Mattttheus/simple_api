    package com.lucasangelo.todosimple.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.Task;


@Repository

public interface TaskRepository extends JpaRepository<Task, Long> {


@Query(value = "SELECT t FROM Task t WHERE t.user.id = :id")
List<Task> findAllByUserId(@Param("id") Long id);

    public List<Task> deleteAllById(Long userId);

    List<Task> findAllBytUserId(Long userid);


  
}
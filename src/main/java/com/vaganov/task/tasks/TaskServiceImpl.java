package com.vaganov.task.tasks;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class TaskServiceImpl implements TaskService{

  private TaskRepository repository;

  public TaskServiceImpl(TaskRepository repository) {
    this.repository = repository;
  }

  @Transactional
  public Task saveTask(TaskDto newTaskDto){
    Task task = new Task();
    task.setTitle(newTaskDto.getTitle());
    task.setDescription(newTaskDto.getDescription());
    task.setDueDate(newTaskDto.getDueDate());
    task.setCompleted(newTaskDto.isCompleted());
    return repository.save(task);
  }

  @Transactional
   public List<Task> findAllTasks(){
     return repository.findAll();
   }

  @Transactional
  public Task findTaskById(Long id){
    return repository.findById(id).orElseThrow(()-> new TaskNotFoundException(id));
  }

  @Transactional
  public Task updateTask(TaskDto newTaskDto, Long id){
    return repository.findById(id).map(task -> {
     task.setTitle(newTaskDto.getTitle());
     task.setDescription(newTaskDto.getDescription());
     task.setDueDate(newTaskDto.getDueDate());
     task.setCompleted(newTaskDto.isCompleted());
      return  repository.save(task);
    }).orElseThrow(()-> new TaskNotFoundException(id));
  }

  @Transactional
  public String deleteTask(Long id){
    if(!repository.existsById(id)){
      throw  new TaskNotFoundException(id);
    }
    repository.deleteById(id);
    return"Id " +id +" deleted successfully!";
  }
}


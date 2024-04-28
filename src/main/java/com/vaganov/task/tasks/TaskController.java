package com.vaganov.task.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PreAuthorize("hasAuthority('tasks:create') or hasAuthority('admin:create')")
  @PostMapping("/task")
  public Task newTask(@RequestBody TaskDto newTaskDto){
    return taskService.saveTask(newTaskDto);
  }

  @PreAuthorize("hasAuthority('tasks:read') or hasAuthority('admin:read')")
  @GetMapping("/tasks")
  public List<Task> getAllTasks() {
    return  taskService.findAllTasks();
  }
  @PreAuthorize("hasAuthority('tasks:read') or hasAuthority('admin:read')")

  @GetMapping("/task/{id}")
  public Task getTaskById(@PathVariable Long id){
    return taskService.findTaskById(id);
  }

  @PreAuthorize("hasAuthority('tasks:read') or hasAuthority('admin:read')")
  @PutMapping("/task/{id}")
  public Task updateTask(@RequestBody TaskDto newTaskDto, @PathVariable Long id) {
    return taskService.updateTask(newTaskDto, id);
  }
  @PreAuthorize("hasAuthority('tasks:delete') or hasAuthority('admin:delete')")
  @DeleteMapping("/task/{id}")
  public String deleteTask(@PathVariable Long id){
     return taskService.deleteTask(id);
  }
}

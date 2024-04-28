package com.vaganov.task.tasks;

import java.util.List;

public interface TaskService {
  Task saveTask(TaskDto newTaskDto);
  Task findTaskById(Long id);
  List<Task> findAllTasks();
  Task updateTask(TaskDto newTaskDto, Long id);
  String deleteTask(Long id);
}
package com.vaganov.task.tasks;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class TaskDto {
  private String title;
  private String description;
  private LocalDate dueDate;
  private boolean completed;
}

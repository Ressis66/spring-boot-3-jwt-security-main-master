package com.vaganov.task.task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest implements Serializable {
  private Integer userId;
  private String title;
  private String body;
}

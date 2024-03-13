package com.vaganov.task.task;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity

@EntityListeners(AuditingEntityListener.class)
public class Post implements Serializable {

  @Id
  @GeneratedValue
  private Integer id;
  private Integer userId;
  private String title;
  private String body;
}

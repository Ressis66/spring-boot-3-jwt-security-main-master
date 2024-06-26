package com.vaganov.task.tasks;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

  @Id
  @GeneratedValue
  private  Long id;
  private String title;
  private String description;
  @JsonFormat(pattern="yyyy-MM-dd")
  private LocalDate dueDate;
  private boolean completed;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Task task = (Task) o;
    return id != null && Objects.equals(id, task.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}

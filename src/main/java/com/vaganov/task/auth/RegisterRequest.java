package com.vaganov.task.auth;

import com.vaganov.task.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String name;
  private String username;
  private String email;
  private String password;
  private Role role;
}

package com.vaganov.task.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vaganov.task.token.Token;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;
import java.util.List;

import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_user")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements UserDetails {

  @Id
  @GeneratedValue
  private Integer id;
  @Column(name="name",insertable=false, updatable=false)
  private String name;
  @Column(name="username")
  private String username;
  @Column(name="email")
  private String email;
  @Column(name="password")
  private String password;
  @Embedded
  @Column(name="address",insertable=false, updatable=false)
  private Address address;
  @Column(name="phone")
  private String phone;
  @Column(name="website")
  private String website;
  @Embedded
  @Column(name="company",insertable=false, updatable=false)
  private Company company;
  @Column(name="role")
  @Enumerated(EnumType.STRING)
  private Role role = Role.USER;
  @Column(name="tokens")
  @OneToMany(mappedBy = "user")
  private List<Token> tokens;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return role.getAuthorities();
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

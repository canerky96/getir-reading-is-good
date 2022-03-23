package com.kaya.authorizationapi.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "customer")
public class Customer implements UserDetails {

  @Id private String id;

  @Indexed(unique = true)
  @NotEmpty(message = "Username must not be empty")
  private String username;

  @NotEmpty(message = "Password must not be empty")
  private String password;

  @NotEmpty(message = "At least one permission is required")
  private List<String> permissions;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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

  @Override
  public String toString() {
    return "Customer{" +
            "id='" + id + '\'' +
            ", username='" + username + '\'' +
            ", permissions=" + permissions +
            '}';
  }
}

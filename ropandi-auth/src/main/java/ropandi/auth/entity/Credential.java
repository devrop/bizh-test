package ropandi.auth.entity;





import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.Id;
@Builder
@Table(name="Credential")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Credential {

  @Id
  private String id;

  private String name;

  private String email;

  private String password;



}

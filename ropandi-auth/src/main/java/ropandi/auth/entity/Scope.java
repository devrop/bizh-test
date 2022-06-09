package ropandi.auth.entity;



import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Builder
@Table(name="Scope")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Scope {
 
 @EmbeddedId
  protected ScopePK scopePK;
  private String value;

}

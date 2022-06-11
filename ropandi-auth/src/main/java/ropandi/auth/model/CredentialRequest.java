package ropandi.auth.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CredentialRequest {

 private String id;
 private String name;
 private  String email;
 private String password;

}

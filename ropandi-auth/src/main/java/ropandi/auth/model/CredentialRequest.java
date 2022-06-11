package ropandi.auth.model;


import lombok.Data;

@Data
public class CredentialRequest {

 private String id;
 private String name;
 private  String email;

 private String password;

}

package bg.imdb.users.rest;

import bg.imdb.users.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  private UserModel user;
  private String jwtToken;
}

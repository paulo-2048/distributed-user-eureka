package br.ucsal.distributeduservalidation.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
public class UserValidationController {

  @Autowired
  @Lazy
  private EurekaClient eurekaClient;

  public static boolean validarUsuario(String email, String cargo, Map<String, String> profileList) {
 
    if (profileList.containsKey(email)) {
      if (profileList.get(email).equals(cargo)) {
        return true;
      }
    }
    return false;
  }

}

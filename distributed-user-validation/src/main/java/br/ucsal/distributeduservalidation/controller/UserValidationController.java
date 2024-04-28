package br.ucsal.distributeduservalidation.controller;

import com.netflix.discovery.EurekaClient;


import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

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

  @GetMapping("/validate")
  public Map<String, String> getProfiles() {
    WebClient webClient = WebClient.create();
    String profilesUrl = "http://localhost:8082";
    Mono<Map<String, String>> profilesMono = webClient.get()
        .uri(profilesUrl)
        .retrieve()
        .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {
        });
    Map<String, String> profiles = profilesMono.block();
    // agora você tem os dados de profileList em profiles
    return profiles;
  }

  @GetMapping("/{email}/{cargo}")
  public String validateUser(@PathVariable String email, @PathVariable String cargo) {
    System.out.println(email + " " + cargo);
    Map<String, String> profileList = getProfiles();
    if (validarUsuario(email, cargo, profileList)) {
      return "Usuário validado com sucesso!";
    } else {
      return "Usuário não validado!";
    }
  }
}

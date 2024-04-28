package br.ucsal.distributedusermain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import br.ucsal.distributedusermain.service.ISPService;

import java.time.LocalDateTime;
import java.util.ArrayList;

@RestController
public class ISPServerController {

  @Autowired
  private ISPService ISPService;

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/health")
  public String healthy() {
    return "Sou o ISP Server e estou online!" + LocalDateTime.now();
  }

  @GetMapping("/{serviceName}") // Profile
  public String getServiceName(@PathVariable String serviceName) {

    try {
      return ISPService.getService(serviceName);
    } catch (Exception e) {
      return "Erro ao acessar o serviço";
    }
  }

  @GetMapping("/{serviceName}/{email}/{cargo}") // Validation
  public String getServiceName(@PathVariable String serviceName, @PathVariable String email, @PathVariable String cargo) {

    try {

      ArrayList<String> params = new ArrayList<>();

      params.add(email);
      params.add(cargo);

      return ISPService.getServiceWithParams(serviceName, params);
    } catch (Exception e) {
      return "Erro ao acessar o serviço";
    }
  }
}

package br.ucsal.distributedusermain.controller;

import java.net.http.HttpRequest.BodyPublisher;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.distributedusermain.service.ISPService;

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
  public String getServiceName(@PathVariable String serviceName, @PathVariable String email,
      @PathVariable String cargo) {

    try {

      ArrayList<String> params = new ArrayList<>();

      params.add(email);
      params.add(cargo);

      return ISPService.getServiceWithParams(serviceName, params);
    } catch (Exception e) {
      return "Erro ao acessar o serviço";
    }
  }

  @GetMapping("{serviceName}/obterArquivo/{nomeArquivo}") // Profile - Obter Arquivo
  public String getArquivo(@PathVariable String serviceName, @PathVariable String nomeArquivo) {

    ArrayList<String> params = new ArrayList<>();
    params.add("obterArquivo");
    params.add(nomeArquivo);

    try {
      return ISPService.getServiceWithParams(serviceName, params);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return "Erro ao acessar o serviço";
    }
  }

  @PostMapping("{serviceName}/{route}") // Profile - Enviar Arquivo
  public String salvarArquivo(@PathVariable String serviceName, @PathVariable String route, @RequestBody BodyPublisher arquivo) {

    ArrayList<String> params = new ArrayList<>();
    params.add(route);

    try {
      return ISPService.postServiceResponse(ISPService.getServiceIP(serviceName), arquivo, params);
    } catch (Exception e) {
      return "Erro ao acessar o serviço";
    }
  }
}

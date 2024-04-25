package br.ucsal.distributedusermain.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class ISPServerController {

  @Value("${spring.application.name}")
  private String appName;

  @GetMapping("/health")
  public String healthy() {
    return "Sou o ISP Server e estou online!" + LocalDateTime.now();
  }
}

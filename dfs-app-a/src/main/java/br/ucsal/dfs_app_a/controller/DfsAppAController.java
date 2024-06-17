package br.ucsal.dfs_app_a.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.dfs_app_a.service.DfsAppAService;

@RestController
public class DfsAppAController {

  @Autowired
  private DfsAppAService service;

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public String verificarArquivo(@PathVariable String nomeArquivo) {
    String caminhoArquivo = service.verificarArquivo(nomeArquivo);

    return service.obterArquivo(caminhoArquivo);
  }

  public String DecidirEnvio(String caminhoArquivo) {
    Random random = new Random();
    int sorteio = random.nextInt(10) + 1;

    if (sorteio % 2 == 0) {
      // Número par, persistir em app-b
      return DfsAppAService.persistirEmDfsB(caminhoArquivo);
    } else {
      // Número ímpar, persistir em app-c
      return DfsAppAService.persistirEmDfsC(caminhoArquivo);
    }
  }
}

package br.ucsal.dfs_app_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.dfs_app_a.service.DfsAppAService;

@Controller
@RestController
public class DfsAppAController {

  @Autowired
  private DfsAppAService service;

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public String verificarArquivo(String nomeArquivo) {
    String caminhoArquivo = service.verificarArquivo(nomeArquivo);

    return service.obterArquivo(caminhoArquivo);
  }

}

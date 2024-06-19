package br.ucsal.dfs_app_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.dfs_app_a.service.DfsAppAService;

@RestController
public class DfsAppAController {

  @Autowired
  private DfsAppAService service;

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public String verificarArquivo(@PathVariable String nomeArquivo) {
    String caminhoArquivo = service.verificarArquivo(nomeArquivo); // Arquivo não encontrado

    if (caminhoArquivo == null) {
      return "Arquivo não encontrado";
    }

    return service.obterArquivo(caminhoArquivo);
  }

  @PostMapping("/salvarArquivo/{nomeArquivo}")
  public String salvarArquivo(@PathVariable String nomeArquivo, @RequestBody String arquivo) {

    String caminhoArquivo = service.salvarArquivo(nomeArquivo, arquivo);

    if (caminhoArquivo == null) {
      return "Erro ao salvar o arquivo";
    }

    // String destino = DecidirEnvio(caminhoArquivo);

    // Retornar o caminho do arquivo e o destino após chamada
    return "Caminho do arquivo: \n" + caminhoArquivo;
  }

}

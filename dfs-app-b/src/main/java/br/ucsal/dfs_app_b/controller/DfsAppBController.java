package br.ucsal.dfs_app_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.dfs_app_b.service.DfsAppBService;

@RestController
public class DfsAppBController {

  @Autowired
  private DfsAppBService service;

  @GetMapping("/verificarArquivo/{nomeArquivo}")
  public ResponseEntity<Boolean> verificarArquivo(@PathVariable String nomeArquivo) {
    // return service.verificarArquivo(nomeArquivo);

    Boolean response = service.verificarArquivo(nomeArquivo);

    System.out.println("\n\n\n\n\n\n\n" + response + "\n\n\n\n\n\n\n");

    return ResponseEntity.ok(response);
  }

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public ResponseEntity<String> obterArquivo(@PathVariable String nomeArquivo) {
    String resource = service.obterArquivo(nomeArquivo);

    if (resource == null) {
      return ResponseEntity.badRequest().body("Arquivo não encontrado");
    }

    return ResponseEntity.ok(resource);
  }

  @PostMapping("/salvarArquivo/{nomeArquivo}")
  public ResponseEntity<String> salvarArquivo(@PathVariable String nomeArquivo, @RequestBody String arquivo) {
    // return service.salvarArquivo(nomeArquivo);

    String response = service.salvarArquivo(nomeArquivo, arquivo);

    if (response == null) {
      return ResponseEntity.badRequest().body("Erro ao salvar arquivo");
    }

    return ResponseEntity.ok(response);
  }
}

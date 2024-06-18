package br.ucsal.dfs_app_c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.ucsal.dfs_app_c.service.DfsAppCService;

@RestController
public class DfsAppCController {

  @Autowired
  private DfsAppCService service;

  @GetMapping("/verificarArquivo/{nomeArquivo}")
  public ResponseEntity<Boolean> verificarArquivo(@PathVariable String nomeArquivo) {
    // return service.verificarArquivo(nomeArquivo);

    return ResponseEntity.ok(service.verificarArquivo(nomeArquivo));
  }

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public ResponseEntity<ByteArrayResource> obterArquivo(@PathVariable String nomeArquivo) {
    ByteArrayResource resource = service.obterArquivo(nomeArquivo);

    return ResponseEntity
        .ok()
        .contentType(MediaType.TEXT_PLAIN)
        .body(resource);
  }
}

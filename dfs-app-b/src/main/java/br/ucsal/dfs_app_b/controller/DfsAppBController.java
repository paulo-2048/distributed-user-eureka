package br.ucsal.dfs_app_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
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

    return ResponseEntity.ok(service.verificarArquivo(nomeArquivo));
  }

  @GetMapping("/obterArquivo/{nomeArquivo}")
  public ResponseEntity<ByteArrayResource> obterArquivo(@PathVariable String nomeArquivo) {
    ByteArrayResource resource = service.obterArquivo(nomeArquivo);

    return ResponseEntity
        .ok()
        .contentType(MediaType.MULTIPART_FORM_DATA)
        .body(resource);
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

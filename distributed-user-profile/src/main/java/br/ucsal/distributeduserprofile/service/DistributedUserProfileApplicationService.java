package br.ucsal.distributeduserprofile.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class DistributedUserProfileApplicationService {

  // obterArquivo
  public String obterArquivo(String nomeArquivo) {

    // app-a = 8086

    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://localhost:8086/obterArquivo/" + nomeArquivo))
        .GET()
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String arquivo = "";
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        arquivo = response.body();
      } else {
        System.out.println("Erro ao acessar o sistema de arquivos distribuídos (App A)");
        System.out.println(response.body());
        arquivo = "Erro ao acessar o sistema de arquivos distribuídos (App A)";
      }

      return arquivo;
    } catch (IOException | InterruptedException e) {
      System.out.println("Erro ao acessar o sistema de arquivos distribuídos (App A)");
      return "Erro ao acessar o sistema de arquivos distribuídos (App A)";
    }

  }

  // salvarArquivo
  public String salvarArquivo(String nomeArquivo) {
    
    return null;
  }

}

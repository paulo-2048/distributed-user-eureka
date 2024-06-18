package br.ucsal.distributeduserprofile.service;

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
        throw new Exception(response.statusCode() + " - " + response.body());
      }

      return arquivo;
    } catch (Exception e) {
      System.out.println(e);
      return e.getMessage();
    }

  }

  // salvarArquivo
  public String salvarArquivo(String nomeArquivo) {

    return null;
  }

}

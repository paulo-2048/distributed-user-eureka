package br.ucsal.distributeduserprofile.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
  public String salvarArquivo(String nomeArquivo, String arquivo) {
    // app-a = 8086

    // arquivo = file txt in base64

    // Mostrar nome do arquivo

    HttpRequest request;
    try {

      request = HttpRequest.newBuilder()
          .uri(URI.create("http://localhost:8086/salvarArquivo/" + nomeArquivo))
          .header("Content-Type", "application/json")
          .POST(HttpRequest.BodyPublishers.ofString(arquivo))
          .build();

      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String arquivoSalvo = "";

      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        arquivoSalvo = response.body();
      } else {
        throw new Exception(response.statusCode() + " - " + response.body());
      }

      return arquivoSalvo;
    } catch (Exception e) {
      e.printStackTrace();

      return e.getMessage();
    }

  }

}

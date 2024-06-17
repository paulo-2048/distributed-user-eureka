package br.ucsal.dfs_app_a.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;

@Service
public class DfsAppAService {

  // Verificar se o arquivo existe
  public String verificarArquivo(String nomeArquivo) {
    // 5. Servidor ISP faz a chamada a aplicação com IP e porta para aplicação
    // profile-app que por
    // sua vez chama dfs-app-a
    // 6. A aplicação dfs-app-a pergunta as aplicações dfs-app-b e dfs-app-c por
    // requisição rest
    // qual delas possui o arquivo solicitado e o obtém, retornando para profile-app
    // 7. Profile-app retorna arquivo para servidor ISP que por sua vez retorna para
    // aplicação para
    // o usuário
    // Endereço app-b = http://localhost:8088
    // Endereço app-c = http://localhost:8090
    // endpoints: /verificarArquivo/{nomeArquivo}
    // endpoints: /obterArquivo/{nomeArquivo}

    String ipAppB = "http://localhost:8088";
    String ipAppC = "http://localhost:8090";

    HttpRequest requestAppB = HttpRequest.newBuilder().uri(URI.create(ipAppB + "/verificarArquivo/" + nomeArquivo))
        .GET().build();
    HttpRequest requestAppC = HttpRequest.newBuilder().uri(URI.create(ipAppC + "/verificarArquivo/" + nomeArquivo))
        .GET().build();

    try {
      HttpResponse<String> responseAppB = HttpClient.newBuilder().build().send(requestAppB,
          HttpResponse.BodyHandlers.ofString());
      HttpResponse<String> responseAppC = HttpClient.newBuilder().build().send(requestAppC,
          HttpResponse.BodyHandlers.ofString());

      if (responseAppB.statusCode() >= 200 && responseAppB.statusCode() < 300) {
        return ipAppB + "/obterArquivo/" + nomeArquivo;
      } else if (responseAppC.statusCode() >= 200 && responseAppC.statusCode() < 300) {
        return ipAppC + "/obterArquivo/" + nomeArquivo;
      } else {
        return "Arquivo não encontrado";
      }

    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    return "Arquivo não encontrado";
  }

  // Obter o arquivo
  public String obterArquivo(String enderecoArquivo) {
    HttpRequest request = HttpRequest.newBuilder().uri(URI.create(enderecoArquivo)).GET().build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String arquivo = "";
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        arquivo = response.body();
      } else {
        System.out.println("Erro ao obter o arquivo resquisitado");
        System.out.println(response.body());
        arquivo = "Erro ao obter o arquivo resquisitado";
      }

      return arquivo;
    } catch (IOException | InterruptedException e) {
      System.out.println("Erro ao obter o arquivo resquisitado");
      return "Erro ao obter o arquivo resquisitado";
    }

  }

}

package br.ucsal.distributedusermain.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class ISPService {

  public String getService(String service) throws URISyntaxException {

    // http://localhost:8100/dns/{service}

    String ipService = getServiceIP(service);

    return getServiceResponse(ipService);

  }

  public String getServiceWithParams(String service, ArrayList<String> Params) throws URISyntaxException {

    // http://localhost:8100/dns/{service}/obterArquivo/{nomeArquivo}


    String ipService = getServiceIP(service);

    return getServiceResponse(ipService, Params);

  }

  public String getServiceIP(String service) throws URISyntaxException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:8100/dns/" + service))
        .GET()
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String ipService = "";
      System.out.println(response.statusCode());
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        ipService = response.body();
      } else {
        System.out.println("Erro ao acessar o DNS Server 1");
        System.out.println(response.body());
        ipService = "Erro ao acessar o DNS Server";
      }

      return ipService;
    } catch (IOException e) {
      System.out.println("Erro ao acessar o DNS Server 2");
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println("Erro ao acessar o DNS Server 3");
      e.printStackTrace();
    }

    return null;
  }

  public String getServiceResponse(String serviceIP) throws URISyntaxException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(serviceIP))
        .GET()
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String serviceResponse = "";
      System.out.println(response.statusCode());
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        serviceResponse = response.body();
      } else {
        throw new IOException("Erro ao acessar o serviço");
      }

      return serviceResponse;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  public String getServiceResponse(String serviceIP, ArrayList<String> Params) throws URISyntaxException {

    String serviceRequest = serviceIP;

    for (String param : Params) {
      serviceRequest += "/" + param;
    }

    System.out.println(serviceRequest);

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(serviceRequest))
        .GET()
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String serviceResponse = "";
      System.out.println(response.statusCode());
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        serviceResponse = response.body();
      } else {
        throw new IOException("Erro ao acessar o serviço");
      }

      return serviceResponse;
    } catch (IOException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println(e.getMessage());
      e.printStackTrace();
    }

    return null;
  }

  public String postServiceResponse(String serviceIp, BodyPublisher bodyPublisher) throws URISyntaxException {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(serviceIp))
        .POST(bodyPublisher)
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String serviceResponse = "";
      System.out.println(response.statusCode());
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        serviceResponse = response.body();
      } else {
        System.out.println("Erro ao acessar o serviço");
        serviceResponse = "Erro ao acessar o serviço";
      }

      return serviceResponse;
    } catch (IOException e) {
      System.out.println("Erro ao acessar o serviço");
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println("Erro ao acessar o serviço");
      e.printStackTrace();
    }

    return null;
  }

  public String postServiceResponse(String serviceIp, BodyPublisher bodyPublisher, ArrayList<String> Params)
      throws URISyntaxException {

    String serviceRequest = serviceIp;

    for (String param : Params) {
      serviceRequest += "/" + param;
    }

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(serviceRequest))
        .POST(bodyPublisher)
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      String serviceResponse = "";
      System.out.println(response.statusCode());
      if (response.statusCode() >= 200 && response.statusCode() < 300) {
        serviceResponse = response.body();
      } else {
        System.out.println("Erro ao acessar o serviço");
        serviceResponse = "Erro ao acessar o serviço";
      }

      return serviceResponse;
    } catch (IOException e) {
      System.out.println("Erro ao acessar o serviço");
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println("Erro ao acessar o serviço");
      e.printStackTrace();
    }

    return null;
  }

}

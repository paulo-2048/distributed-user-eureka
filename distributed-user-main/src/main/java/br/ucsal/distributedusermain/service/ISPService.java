package br.ucsal.distributedusermain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ISPService {

  public String getService(String service) throws URISyntaxException {

    // http://localhost:8100/dns/{service}

    String ipService = getServiceIP(service);

    return getServiceResponse(ipService);

  }

  public Resource getServiceFileWithParams(String service, ArrayList<String> Params) throws URISyntaxException {

    // http://localhost:8100/dns/{service}/obterArquivo/{nomeArquivo}

    String fileName = Params.get(1);

    String ipService = getServiceIP(service);

    String fileBase64 = getServiceResponse(ipService, Params);

    if (fileBase64 == null) {
      return null;
    }

    byte[] decodedBytes = Base64.getDecoder().decode(fileBase64);

    Path basePath = new File("distributed-user-main/temp/").toPath();

    // Constrói o caminho completo para o arquivo
    Path fullPath = Paths.get(basePath.toString(), fileName);

    File tempFile;
    try {

      if (!Files.exists(fullPath.getParent())) {
        Files.createDirectories(fullPath.getParent());
      }

      tempFile = new File(fullPath.toString());
      FileOutputStream fos = new FileOutputStream(tempFile);
      fos.write(decodedBytes);
      fos.close();

      return new FileSystemResource(tempFile);

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
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

  public String getServiceWithParams(String service, ArrayList<String> Params) throws URISyntaxException {

    // http://localhost:8100/dns/{service}/obterArquivo/{nomeArquivo}

    String ipService = getServiceIP(service);

    return getServiceResponse(ipService, Params);

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
      System.out.println("\n\n\n\n" + response.body());
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

  public String postServiceResponseFile(String serviceIp, MultipartFile file, ArrayList<String> Params)
      throws URISyntaxException {
    String serviceRequest = serviceIp;

    for (String param : Params) {
      serviceRequest += "/" + param;
    }

    String fileBase64 = fileToBase64(file);

    System.out.println("\n\n\n\n\n\n" + fileBase64 + "\n\n\n\n\n\n");

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(serviceRequest))
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(fileBase64))
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
        throw new Exception("Erro ao acessar o serviço");
      }

      return serviceResponse;
    } catch (Exception e) {
      System.out.println("Erro ao acessar o serviço");
      e.printStackTrace();
    }

    return null;
  }

  public Resource createFile(String content, String fileName) {

    Path basePath = new File("distributed-user-main/temp/").toPath();

    // Constrói o caminho completo para o arquivo
    Path fullPath = Paths.get(basePath.toString(), fileName);

    // /if (!Files.exists(fullPath.getParent())) {
    // try {
    // Files.createDirectory(fullPath.getParent());
    // } catch (IOException e) {
    // e.printStackTrace();
    // return null;
    // }
    // }

    try {

      if (!Files.exists(fullPath.getParent())) {
        Files.createDirectories(fullPath.getParent());
      }

      Path tempFile = new File(fullPath.toString()).toPath();
      Files.write(tempFile, content.getBytes());
      return new FileSystemResource(tempFile);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

  private String fileToBase64(MultipartFile file) {

    try {
      byte[] fileContent = file.getBytes();
      return Base64.getEncoder().encodeToString(fileContent);
    } catch (IOException e) {
      e.printStackTrace();
    }

    return null;
  }

}

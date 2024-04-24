package br.ucsal.distributeduserdns.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class DNSServerService {

  public String getDns(String service) throws URISyntaxException {

    // http://localhost:8761/listApplications

    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI("http://localhost:8761/listApplications"))
        .GET()
        .build();

    try {
      HttpResponse<String> response = HttpClient.newBuilder().build().send(request,
          HttpResponse.BodyHandlers.ofString());

      return getServiceUrl(service, response.body());
    } catch (IOException e) {
      System.out.println("Erro ao acessar o Eureka Server");
      e.printStackTrace();
    } catch (InterruptedException e) {
      System.out.println("Erro ao acessar o Eureka Server");
      e.printStackTrace();
    }

    return null;
  }

  private static String getServiceUrl(String serviceName, String input) {
    String adjustedServiceName = "DISTRIBUTED-USER-" + serviceName.toUpperCase();

    String pattern = "name=" + adjustedServiceName + ",.*?ipAddr = (\\d+\\.\\d+\\.\\d+\\.\\d+),.*?port = (\\d+)";
    Pattern r = Pattern.compile(pattern, Pattern.DOTALL);
    Matcher m = r.matcher(input);

    if (m.find()) {
      String ip = m.group(1);
      String port = m.group(2);
      return "http://" + ip + ":" + port;
    }
    return "URL não encontrada";
  }
}

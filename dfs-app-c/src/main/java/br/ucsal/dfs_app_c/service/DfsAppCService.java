package br.ucsal.dfs_app_c.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;

@Service
public class DfsAppCService {

  public Boolean verificarArquivo(String nomeArquivo) {
    // Verifica se o arquivo existe
    // Ex file: arquivo_v1.txt

    Path basePath = new File("dfs-app-c/arquivos/").toPath();

    // Constrói o caminho completo para o arquivo
    Path fullPath = Paths.get(basePath.toString(), nomeArquivo);

    // Verifica se o arquivo existe
    // Se existir, retorna true

    if (Files.exists(fullPath)) {
      return true;
    }

    return false;
  }

  public String obterArquivo(String nomeArquivo) {
    Path basePath = new File("dfs-app-c/arquivos/").toPath();

    // Constrói o caminho completo para o arquivo
    Path fullPath = Paths.get(basePath.toString(), nomeArquivo);

    if (Files.exists(fullPath)) {
      File file = fullPath.toFile();
      String base64 = fileToBase64(file);
      return base64;
    } else {
      return null;
    }
  }

  public String salvarArquivo(String nomeArquivo, String arquivo) {

    // Converter o aqruivo base64 para arquivo
    // Salvar o arquivo dentro da pasta "arquivo" dentro da pasta raiz do projeto
    // Se a pasta não existir, criar a pasta
    // spring boot

    Path basePath = new File("dfs-app-c/arquivos/").toPath();

    // Constrói o caminho completo para o arquivo
    Path fullPath = Paths.get(basePath.toString(), nomeArquivo);

    if (!Files.exists(fullPath.getParent())) {
      try {
        Files.createDirectory(fullPath.getParent());
      } catch (IOException e) {
        e.printStackTrace();
        return null;
      }
    }

    File file = base64ToFile(arquivo, fullPath.toString());

    System.out.println(file);
    if (file != null) {
      return nomeArquivo + " Salvo em App C";
    } else {
      return null;
    }
  }

  private File base64ToFile(String base64, String nomeArquivo) {
    try {
      byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64);
      File file = new File(nomeArquivo);
      Files.write(file.toPath(), decodedBytes);
      return file;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private String fileToBase64(File arquivo) {
    try {
      byte[] fileContent = Files.readAllBytes(arquivo.toPath());
      return java.util.Base64.getEncoder().encodeToString(fileContent);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  } 

}

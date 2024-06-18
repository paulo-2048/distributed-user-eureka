package br.ucsal.dfs_app_c.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

@Service
public class DfsAppCService {

  public Boolean verificarArquivo(String nomeArquivo) {
    // Verifica se o arquivo existe
    // Ex file: arquivo_v1.txt

    String basePath = "/home/arquivos/";

    // Verifica se o arquivo existe
    // Se existir, retorna true

    String fullPath = basePath + nomeArquivo;

    File file = new File(fullPath);
    if (file.exists()) {
      return true;
    }

    return false;
  }

  public ByteArrayResource obterArquivo(String nomeArquivo) {
    String basePath = "./arquivos/";

    // Verifica se o arquivo existe
    // Se existir, retorna o caminho do arquivo

    String fullPath = basePath + nomeArquivo;

    try {
      File file = new File(fullPath);
      Path path = Paths.get(file.getAbsolutePath());
      ByteArrayResource resource = new ByteArrayResource(Files.readAllBytes(path));

      return resource;
    } catch (Exception e) {
      e.printStackTrace();
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

}

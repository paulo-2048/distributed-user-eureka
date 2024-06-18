package br.ucsal.dfs_app_b.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

@Service
public class DfsAppBService {

    public Boolean verificarArquivo(String nomeArquivo) {
        // Verifica se o arquivo existe
        // Ex file: arquivo_v1.txt

        String basePath = "./arquivos/";

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

    private String getFileName(String nomeArquivo) {
        return nomeArquivo.substring(0, nomeArquivo.lastIndexOf("_"));

        // Ex: arquivo_v1.txt -> arquivo
    }

    private String getFileVersion(String nomeArquivo) {
        return nomeArquivo.substring(nomeArquivo.lastIndexOf("_") + 1, nomeArquivo.lastIndexOf("."));

        // Ex: arquivo_v1.txt -> 1
    }
}

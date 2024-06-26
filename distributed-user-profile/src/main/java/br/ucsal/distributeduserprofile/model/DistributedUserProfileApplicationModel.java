package br.ucsal.distributeduserprofile.model;

import java.util.HashMap;
import java.util.Map;

public class DistributedUserProfileApplicationModel {

    // HashMap para armazenar e-mails e cargos
    private static final Map<String, String> profileList = new HashMap<>();

    private String email;
    private String cargo;

    // Construtor
    public DistributedUserProfileApplicationModel(String email, String cargo) {
        this.email = email;
        this.cargo = cargo;
        profileList.put(email, cargo); // Adiciona ao HashMap
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    // Método para adicionar um novo usuário ao HashMap
    public static void adicionarUsuario(String email, String cargo) {
        profileList.put(email, cargo);
    }

    // Método para obter o cargo de um usuário a partir do e-mail
    public static String obterCargoPorEmail(String email) {
        return profileList.get(email);
    }

    public static Map<String, String> obterTodosOsUsuarios() {
        return profileList;
    }

    // toString para representação textual do objeto
    @Override
    public String toString() {
        return "DistributedUserProfile{" +
                "email='" + email + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }

    // Adicionar usuários de exemplo ao HashMap
    static {
        adicionarUsuario("everton@pro.ucsal.br", "Professor");
        adicionarUsuario("yuri@ucsal.edu.br", "Aluno");
        adicionarUsuario("guilherme@ucsal.edu.br", "Aluno");
        adicionarUsuario("paulo@ucsal.edu.br", "Aluno");
    }
}
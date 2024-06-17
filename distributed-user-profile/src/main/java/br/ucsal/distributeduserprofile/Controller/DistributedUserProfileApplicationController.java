package br.ucsal.distributeduserprofile.Controller;

import br.ucsal.distributeduserprofile.model.DistributedUserProfileApplicationModel;
import br.ucsal.distributeduserprofile.service.DistributedUserProfileApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DistributedUserProfileApplicationController {

    @Autowired
    private DistributedUserProfileApplicationService service;

    @GetMapping("/")
    public Map<String, String> getAllProfiles() {
        return DistributedUserProfileApplicationModel.obterTodosOsUsuarios();
    }

    @GetMapping("/profilesInfo")
    public String getProfilesInfo() {
        StringBuilder profilesInfo = new StringBuilder();
        Map<String, String> profiles = DistributedUserProfileApplicationModel.obterTodosOsUsuarios();

        for (Map.Entry<String, String> profile : profiles.entrySet()) {
            profilesInfo.append("Email: ").append(profile.getKey())
                    .append(", Cargo: ").append(profile.getValue()).append("\n");
        }

        return profilesInfo.toString();
    }

    // obterArquivo
    @GetMapping("/obterArquivo/{nomeArquivo}")
    public String obterArquivo(String nomeArquivo) {

        return service.obterArquivo(nomeArquivo);

    }

    // salvarArquivo
    // @PostMapping("/salvarArquivo/{nomeArquivo}")
    // public String salvarArquivo(String nomeArquivo) {

    // }
}

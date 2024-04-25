package br.ucsal.distributeduserprofile.Controller;

import br.ucsal.distributeduserprofile.model.DistributedUserProfileApplicationModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class DistributedUserProfileApplicationController {

    @GetMapping("/profiles")
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
}


package br.ucsal.distributeduserserver.controller;

import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class ApplicationDiscoveryController {
    @Autowired
    @Lazy
    private EurekaClient eurekaClient;

    @Value("${spring.application.name}")
    private String appName;

    @GetMapping("/health")
    public String healthy() {

        return "Sou o Eureka Server e estou online!" + LocalDateTime.now();
    }

    @GetMapping("/listApplications")
    public String listApplications() {
        Applications otherApps = eurekaClient.getApplications();
        
        InstanceStatus otherAppsStatus = eurekaClient.getInstanceRemoteStatus();
        
        System.out.println("EUREKA: " + otherApps);
        System.out.println("Applications: " + otherApps.getRegisteredApplications());
        return otherApps.getRegisteredApplications().toString();
    }

}
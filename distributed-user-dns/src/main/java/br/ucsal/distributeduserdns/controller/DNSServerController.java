package br.ucsal.distributeduserdns.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import br.ucsal.distributeduserdns.service.DNSServerService;

import java.net.URISyntaxException;
import java.time.LocalDateTime;

@RestController
public class DNSServerController {

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private DNSServerService dnsServerService;

    @GetMapping("/health")
    public String healthy() {
        return "Sou o DNS Server e estou online: " + LocalDateTime.now();
    }

    @GetMapping("/dns/{service}")
    public String getDns(@PathVariable String service) throws URISyntaxException {

        return dnsServerService.getDns(service);
    }
}

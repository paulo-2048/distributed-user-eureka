package br.ucsal.distributeduservalidation.controller;

import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Applications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class UserValidationController {

  @Autowired
  @Lazy
  private EurekaClient eurekaClient;

}

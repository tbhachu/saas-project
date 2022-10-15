package edu.greenriver.edu.saasproject;

import edu.greenriver.edu.saasproject.models.Raag;
import edu.greenriver.edu.saasproject.services.RaagService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class SaasProjectApplication {

    public static void main(String[] args) {

        SpringApplication.run(SaasProjectApplication.class, args);

        RaagService raags = new RaagService();

        raags.addRaag("Kaafi", "Khamaj", "Evening", "Pa");


        System.out.println(raags);
    }
}

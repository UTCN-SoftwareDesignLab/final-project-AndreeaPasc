package com.finalprojectandreeapasc;

import com.finalprojectandreeapasc.api.controller.GeocodeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FinalProject {

    public static void main(String[] args) throws IOException {
        GeocodeController controller = new GeocodeController();
        controller.getGeocode("164 Townsend St. San Francisco, CA");

        SpringApplication.run(FinalProject.class, args);
    }
}

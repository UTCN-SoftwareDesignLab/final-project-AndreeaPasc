package com.finalprojectandreeapasc;

import com.finalprojectandreeapasc.api.controller.GeocodeController;
import com.finalprojectandreeapasc.pdf.controller.PdfController;
import com.finalprojectandreeapasc.pdf.service.PdfService;
import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.finalprojectandreeapasc.recipes.repository.RecipeRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class FinalProjectAndreeaPascApplication {

    public static void main(String[] args) throws IOException {
        GeocodeController controller = new GeocodeController();
        controller.getGeocode("164 Townsend St. San Francisco, CA");

        SpringApplication.run(FinalProjectAndreeaPascApplication.class, args);
    }
}

package com.finalprojectandreeapasc.pdf.service;

import com.finalprojectandreeapasc.recipes.model.Recipe;
import com.finalprojectandreeapasc.recipes.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PdfService implements ReportService{

    private final RecipeRepository recipeRepository;

    public ByteArrayOutputStream pdfGenerate() throws IOException {
        ByteArrayOutputStream file = new ByteArrayOutputStream();
        List<Recipe> recipes = recipeRepository.findAll();
        PDFont font =  PDType1Font.HELVETICA ;

            PDDocument doc = new PDDocument();
            PDPage page = new PDPage();
            doc.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(doc, page);

            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.setLeading(15);
            contentStream.newLineAtOffset(20, 700);
            contentStream.showText("RECIPES");
            contentStream.newLine();
            contentStream.newLine();

            for (Recipe recipe : recipes) {
                contentStream.showText("Title: " + recipe.getTitle().replace("\n", "").replace("\r", ""));
                contentStream.newLine();
                contentStream.showText("Ingredients: " + recipe.getIngredients().replace("\n", "").replace("\r", ""));
                contentStream.newLine();
                contentStream.showText("Quantity: " + recipe.getQuantity().toString().replace("\n", "").replace("\r", ""));
                contentStream.newLine();
                contentStream.showText("Price: " + recipe.getPrice().toString().replace("\n", "").replace("\r", ""));
                contentStream.newLine();
                contentStream.newLine();
                contentStream.newLine();
            }
            contentStream.endText();
            contentStream.close();
            doc.save(file);
            doc.close();

        return file;
    }
}
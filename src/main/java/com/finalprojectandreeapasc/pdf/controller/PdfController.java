package com.finalprojectandreeapasc.pdf.controller;

import com.finalprojectandreeapasc.email.service.EmailService;
import com.finalprojectandreeapasc.pdf.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static com.finalprojectandreeapasc.UrlMapping.EXPORT_REPORT;

@RestController
@RequestMapping(EXPORT_REPORT)
@RequiredArgsConstructor
public class PdfController {
    private final PdfService pdfService;

    @GetMapping
    public ResponseEntity<?> pdfGenerate() throws IOException {
        ByteArrayOutputStream bodyOutput = pdfService.pdfGenerate();
        ByteArrayResource byteArrayResource = new ByteArrayResource(bodyOutput.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=PrettyPDF.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(byteArrayResource.contentLength())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(byteArrayResource);
    }
}

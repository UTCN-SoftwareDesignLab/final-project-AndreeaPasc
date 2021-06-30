package com.finalprojectandreeapasc.pdf.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface ReportService {
    ByteArrayOutputStream pdfGenerate() throws IOException;
}

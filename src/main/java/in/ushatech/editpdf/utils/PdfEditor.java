package in.ushatech.editpdf.utils;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfEditor {
    public static void main(String[] args) {
        try {
            File inputFile = new File("C:\\Users\\DELL\\Desktop\\input.pdf");
            File outputFile = new File("C:\\Users\\DELL\\Desktop\\output.pdf");

            PDDocument doc = PDDocument.load(inputFile);

            // Replace text in the PDF
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(doc);

            // Filter out control characters (U+0000 to U+001F) from the text
            text = text.replaceAll("\\p{Cntrl}", "");
            text = text.replaceAll("Edit", "Edited");

            // Save the modified text back to the PDF
            try (PDDocument newDoc = new PDDocument()) {
                PDPage blankPage = new PDPage();
                newDoc.addPage(blankPage);
                PDPageContentStream contentStream = new PDPageContentStream(newDoc, blankPage);

                // Draw the modified text on the new page
                contentStream.beginText();
                contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
                contentStream.newLineAtOffset(100, 700); // Adjust the position as needed
                contentStream.showText(text);
                contentStream.endText();
                contentStream.close();

                newDoc.save(outputFile);
            }

            doc.close();
            System.out.println("PDF editing complete.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

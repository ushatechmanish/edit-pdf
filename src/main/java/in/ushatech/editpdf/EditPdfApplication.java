package in.ushatech.editpdf;

import in.ushatech.editpdf.utils.PdfEditor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EditPdfApplication
{

    public static void main(String[] args) {
        SpringApplication.run(EditPdfApplication.class, args);
    }

    @RequestMapping("/")
    public String getText()
    {
        PdfEditor.main(new String[]{});
        return "Edited pdf application";
    }
}

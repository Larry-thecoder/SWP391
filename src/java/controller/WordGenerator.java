package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordGenerator {

    public List<String> getLines(File fileName) throws Exception {
        ReadFile rf = new ReadFile();
        List<String> lines = rf.readLines(fileName);
        lines.forEach(System.out::println);
        return lines;
    }

    //Create Word
    public void createWord(List<String> lines, HttpServletRequest request) throws IOException {
        String pathToWEBINF = request.getServletContext().getRealPath("/WEB-INF");
        //Check the generated path. If it is not there, create it.
//        if (!Paths.get("./generated").toFile().exists()) Files.createDirectories(Paths.get("./generated"));
//        File fileTemp;
//        if(request.getServletContext().getRealPath("/WEB-INF/generated").isEmpty()) fileTemp = new File(pathToWEBINF + "/generated");
        //Create Word docs.
        for (String line : lines) {
            //Blank Document
            XWPFDocument document = new XWPFDocument();

//            File generatedFiles = new File(request.getServletContext().getInitParameter("generated.location") + "classpath*:**/createdWord" + "_" + line + ".docx");
            File generatedFiles = new File(pathToWEBINF + "/generated/createdWord" + "_" + line + ".docx");
            //Write the Document in file system
            FileOutputStream out = new FileOutputStream(generatedFiles);
            //create Paragraph
            XWPFParagraph paragraph = document.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("VK Number (Parameter): " + line + " here you type your text...\n");
            document.write(out);
            //Close document
            out.close();
            System.out.println("createdWord" + "_" + line + ".docx" + " written successfully");
        }
    }
}

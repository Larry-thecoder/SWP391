package controller;

import com.google.gson.Gson;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import topicDescription.StudentDTO;
import topicDescription.SupervisorDTO;
import topicDescription.TopicDescriptionDAO;
import topicDescription.TopicDescriptionDTO;
import topicDescription.TopicDescriptionDetails;

@WebServlet(name = "ViewTopicDescriptionsApprovedController", urlPatterns = {"/ViewTopicDescriptionsApprovedController"})
public class ViewTopicDescriptionsApprovedController extends HttpServlet {
    private static final String ERROR = "staffTP.jsp";
    private static final String SUCCESS = "staffTP.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            TopicDescriptionDAO dao = new TopicDescriptionDAO();
            List<TopicDescriptionDTO> listTD = dao.getListTopicDescriptionByStatus("Approved");
            if (!listTD.isEmpty()) {
                HashMap<String, String> downloadFiles = new HashMap<>();
                int i = 0;
        
                for (TopicDescriptionDTO topicDescriptionDTO : listTD) {
                    Gson g = new Gson();
                    TopicDescriptionDetails t = g.fromJson(topicDescriptionDTO.getDetails(), TopicDescriptionDetails.class);
                    List<String> topicDescriptionDetailsLines= new ArrayList<>();
                    topicDescriptionDetailsLines.add("FPT University");
                    topicDescriptionDetailsLines.add("Graduation Thesis Register");
                    topicDescriptionDetailsLines.add("Class: "+ t.getClassName() +"               "
                                                   + "Duration time: From ");
                    topicDescriptionDetailsLines.add("(*) Profession: < "+ t.getProfession() +" >          "
                                                   + "Special: ");
                    topicDescriptionDetailsLines.add("(*) Kinds of person make registers: "+ t.getKindOfPersonMakeRegisters());
                    topicDescriptionDetailsLines.add("1. Register information for supervisors (if any)");
                    topicDescriptionDetailsLines.add("2. Register information for students (if any)");
                    topicDescriptionDetailsLines.add("3. Register content of Gradution Thesis:");
                    topicDescriptionDetailsLines.add("(*) 3.1. Gradution Thesis name:");
                    topicDescriptionDetailsLines.add("English: "+ t.getThesisNameEnglish());
                    topicDescriptionDetailsLines.add("Vietnamese: "+ t.getThesisNameVN());
                    topicDescriptionDetailsLines.add("Abbreviation: "+ t.getThesisNameAbbr());
                    topicDescriptionDetailsLines.add("(*) 3.2. Main proposal content (including result and product)");
                    topicDescriptionDetailsLines.add("a) Theory: ");
                    topicDescriptionDetailsLines.add(t.getMainContent_Theory());
                    topicDescriptionDetailsLines.add("b) Practice: ");
                    topicDescriptionDetailsLines.add(t.getMainContent_Practice());
                    topicDescriptionDetailsLines.add("4. Other comment (propose all relative thing if any)");
                    topicDescriptionDetailsLines.add(t.getOtherComment());
                    topicDescriptionDetailsLines.add(t.getSigningPlace() +", "+ t.getSignedDate());
                    topicDescriptionDetailsLines.add("             Supervisor (If any)                                       "
                                                   + "On behalf of Registers");
                    topicDescriptionDetailsLines.add("            (Sign and full name)                                         "
                                                   + "(Sign and full name)");
                    
                    String downloadFileName= "";
                    List<SupervisorDTO> supervisors= t.getSupervisors();
                    if (supervisors!= null && !supervisors.isEmpty()) {
                        for (SupervisorDTO supervisor : supervisors) {
                            String[] parts = supervisor.getEmail().split("@");
                            downloadFileName += parts[0] + "_";
                        }
                    }
                    downloadFileName+= t.getThesisNameAbbr();
                    
                    String pathToGENERATED= request.getServletContext().getRealPath("/WEB-INF/generated/");
                    //Blank Document
                    XWPFDocument document = new XWPFDocument();

                    File generatedFiles = new File(pathToGENERATED + downloadFileName + ".docx");
                    //Write the Document in file system
                    FileOutputStream out = new FileOutputStream(generatedFiles);
                    //create Paragraph
                    createParagraph(document, topicDescriptionDetailsLines, t.getDurationTime(), t.getSpecial(), supervisors, t.getStudents());
                    document.write(out);
                    //Close document
                    out.close();
//                    System.out.println(downloadFileName + ".docx" + " written successfully");

                    downloadFiles.put(downloadFileName +".docx", downloadFileName);
                    i++;
                }
                request.setAttribute("DOWNLOAD_FILES", downloadFiles);
                url = SUCCESS;            
            }
        } catch (Exception e) {
            log("Error at ViewTopicDescriptionsApprovedController:" + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }
    
    void createParagraph(XWPFDocument document, List<String> topicDescriptionDetailsLines, 
            List<String> durationTime, List<String> special, List<SupervisorDTO> supervisor, 
            List<StudentDTO> students) {
        XWPFParagraph p1 = document.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r1 = p1.createRun();
        r1.setFontFamily("Courier");
        r1.setBold(true);
        r1.setColor("f37123");
        r1.setCapitalized(true);
        r1.setText(topicDescriptionDetailsLines.get(0));
        
        XWPFParagraph p2 = document.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun r2 = p2.createRun();
        r2.setBold(true);
        r2.setCapitalized(true);
        r2.setText(topicDescriptionDetailsLines.get(1));
        
        XWPFParagraph p3 = document.createParagraph();
        XWPFRun r3 = p3.createRun();
        String durationTimeTxt= "";
        if (!durationTime.isEmpty()) {
            durationTimeTxt+= durationTime.get(0);
            durationTimeTxt+= "  To "+ durationTime.get(1);
        }
        r3.setText(topicDescriptionDetailsLines.get(2) + durationTimeTxt);
        
        XWPFParagraph p4 = document.createParagraph();
        XWPFRun r4 = p4.createRun();
        String specialTxt= "";
        if (!special.isEmpty()) {
            for (int i = 0; i < special.size(); i++) {
                specialTxt+= "< "+ special.get(i) +" > ";
            }
        }
        r4.setText(topicDescriptionDetailsLines.get(3) + specialTxt);
        
        XWPFParagraph p5 = document.createParagraph();
        XWPFRun r5 = p5.createRun();
        r5.setText(topicDescriptionDetailsLines.get(4));
        
        XWPFParagraph p6 = document.createParagraph();
        XWPFRun r6 = p6.createRun();
        r6.setText(topicDescriptionDetailsLines.get(5));
        if (!supervisor.isEmpty()) {
            int numOfRows= supervisor.size() +1;
            XWPFTable table= document.createTable(numOfRows, 5);

            // write to 1st row, 1st column
            XWPFParagraph pTable1 = table.getRow(0).getCell(0).getParagraphs().get(0);
            pTable1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable1 = pTable1.createRun();
            rTable1.setBold(true);

            // write to 1st row, 2nd column
            XWPFParagraph pTable2 = table.getRow(0).getCell(1).getParagraphs().get(0);
            pTable2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable2 = pTable2.createRun();
            rTable2.setBold(true);
            rTable2.setText("Full Name");

            // write to 1st row, 3rd column
            XWPFParagraph pTable3 = table.getRow(0).getCell(2).getParagraphs().get(0);
            pTable3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable3 = pTable3.createRun();
            rTable3.setBold(true);
            rTable3.setText("Phone");

            // write to 1st row, 4th column
            XWPFParagraph pTable4 = table.getRow(0).getCell(3).getParagraphs().get(0);
            pTable4.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable4 = pTable4.createRun();
            rTable4.setBold(true);
            rTable4.setText("Email");
            
            // write to 1st row, 5th column
            XWPFParagraph pTable5 = table.getRow(0).getCell(4).getParagraphs().get(0);
            pTable5.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable5 = pTable5.createRun();
            rTable5.setBold(true);
            rTable5.setText("Title");
            
            for (int i = 1; i < numOfRows; i++) {
                table.getRow(i).getCell(0).setText("Supervisor "+ i);
                table.getRow(i).getCell(1).setText(supervisor.get(i-1).getFullName());
                table.getRow(i).getCell(2).setText(supervisor.get(i-1).getPhone());
                table.getRow(i).getCell(3).setText(supervisor.get(i-1).getEmail());
                table.getRow(i).getCell(4).setText(supervisor.get(i-1).getTitle());
            }
        }
        
        XWPFParagraph p7 = document.createParagraph();
        XWPFRun r7= p7.createRun();
        r7.setText(topicDescriptionDetailsLines.get(6));
        if (!students.isEmpty()) {
            int numOfRows= students.size() +1;
            XWPFTable table= document.createTable(numOfRows, 6);

            // Write to 1st row
            XWPFParagraph pTable1 = table.getRow(0).getCell(0).getParagraphs().get(0);
            pTable1.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable1 = pTable1.createRun();
            rTable1.setBold(true);

            XWPFParagraph pTable2 = table.getRow(0).getCell(1).getParagraphs().get(0);
            pTable2.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable2 = pTable2.createRun();
            rTable2.setBold(true);
            rTable2.setText("Full Name");

            XWPFParagraph pTable3 = table.getRow(0).getCell(2).getParagraphs().get(0);
            pTable3.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable3 = pTable3.createRun();
            rTable3.setBold(true);
            rTable3.setText("Student Code");

            XWPFParagraph pTable4 = table.getRow(0).getCell(3).getParagraphs().get(0);
            pTable4.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable4 = pTable4.createRun();
            rTable4.setBold(true);
            rTable4.setText("Phone");
            
            XWPFParagraph pTable5 = table.getRow(0).getCell(4).getParagraphs().get(0);
            pTable5.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable5 = pTable5.createRun();
            rTable5.setBold(true);
            rTable5.setText("Email");

            XWPFParagraph pTable6 = table.getRow(0).getCell(5).getParagraphs().get(0);
            pTable6.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun rTable6 = pTable6.createRun();
            rTable6.setBold(true);
            rTable6.setText("Role in Group");
            
            for (int i = 1; i < numOfRows; i++) {
                table.getRow(i).getCell(0).setText("Student "+ i);
                table.getRow(i).getCell(1).setText(students.get(i-1).getFullName());
                table.getRow(i).getCell(2).setText(students.get(i-1).getStudentCode());
                table.getRow(i).getCell(3).setText(students.get(i-1).getPhone());
                table.getRow(i).getCell(4).setText(students.get(i-1).getEmail());
                table.getRow(i).getCell(5).setText(students.get(i-1).getRoleInGroup());
            }
        }
        
        // 3. Register content of Gradution Thesis
        XWPFParagraph p8 = document.createParagraph();
        XWPFRun r8= p8.createRun();
        r8.setText(topicDescriptionDetailsLines.get(7));
        
        // (*) 3.1. Gradution Thesis name:
        XWPFParagraph p9 = document.createParagraph();
        XWPFRun r9= p9.createRun();
        r9.setText(topicDescriptionDetailsLines.get(8));
        
        // English: 
        XWPFParagraph p10 = document.createParagraph();
        XWPFRun r10= p10.createRun();
        r10.setText(topicDescriptionDetailsLines.get(9));
        
        // Vietnamese:
        XWPFParagraph p11 = document.createParagraph();
        XWPFRun r11= p11.createRun();
        r11.setText(topicDescriptionDetailsLines.get(10));
        
        // Abbreviation:
        XWPFParagraph p12 = document.createParagraph();
        XWPFRun r12= p12.createRun();
        r12.setText(topicDescriptionDetailsLines.get(11));
        
        // (*) 3.2. Main proposal content (including result and product)
        XWPFParagraph p13 = document.createParagraph();
        XWPFRun r13= p13.createRun();
        r13.setText(topicDescriptionDetailsLines.get(12));
        
        // a) Theory: 
        XWPFParagraph p14 = document.createParagraph();
        XWPFRun r14= p14.createRun();
        r14.setText(topicDescriptionDetailsLines.get(13));
        XWPFParagraph p15 = document.createParagraph();
        XWPFRun r15= p15.createRun();
        r15.setText(topicDescriptionDetailsLines.get(14));
        
        // b) Practice: 
        XWPFParagraph p16 = document.createParagraph();
        XWPFRun r16= p16.createRun();
        r16.setText(topicDescriptionDetailsLines.get(15));
        XWPFParagraph p17 = document.createParagraph();
        XWPFRun r17= p17.createRun();
        r17.setText(topicDescriptionDetailsLines.get(16));
        
        // 4. Other comment (propose all relative thing if any)
        XWPFParagraph p18 = document.createParagraph();
        XWPFRun r18= p18.createRun();
        r18.setText(topicDescriptionDetailsLines.get(17));
        XWPFParagraph p19 = document.createParagraph();
        XWPFRun r19= p19.createRun();
        r19.setText(topicDescriptionDetailsLines.get(18));
        
        // Date & Place of Signing
        XWPFParagraph p20 = document.createParagraph();
        p20.setAlignment(ParagraphAlignment.RIGHT);
        XWPFRun r20 = p20.createRun();
        r20.setText(topicDescriptionDetailsLines.get(19));
        
        // Supervisor, Registers
        XWPFParagraph p21 = document.createParagraph();
        XWPFRun r21= p21.createRun();
        r21.setBold(true);
        r21.setText(topicDescriptionDetailsLines.get(20));
        XWPFParagraph p22 = document.createParagraph();
        XWPFRun r22= p22.createRun();
        r22.setText(topicDescriptionDetailsLines.get(21));
        
        document.createParagraph().createRun();
        document.createParagraph().createRun();
        
        if (!supervisor.isEmpty()) {
            for (int i = 0; i < supervisor.size(); i++) {
                document.createParagraph().createRun()
                        .setText("      "+ supervisor.get(i).getFullName());
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

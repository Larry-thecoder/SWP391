package topicDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class StudentDAO {
    public static final String ALL = "SELECT studentCode, fullName, phone, email, roleInGroup FROM tblStudent";
    public static final String GET_BY_EMAIL = "SELECT studentCode, fullName, phone, roleInGroup FROM tblStudent "
                                            + "WHERE email=?";

    public List<StudentDTO> getListStudent() throws SQLException {
        List<StudentDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(ALL);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String studentCode= rs.getString("studentCode");
                    String fullName= rs.getString("fullName");
                    String phone= rs.getString("phone");
                    String email= rs.getString("email");
                    String roleInGroup= rs.getString("roleInGroup");
                    list.add(new StudentDTO(fullName, studentCode, phone, email, roleInGroup));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return list;
    }
    
    public StudentDTO getStudentByEmail(String email) throws SQLException {
        StudentDTO studentDTO = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(GET_BY_EMAIL);
                pst.setString(1, email);
                rs= pst.executeQuery();
                if (rs.next()) {                    
                    String studentCode= rs.getString("studentCode");
                    String fullName= rs.getString("fullName");
                    String phone= rs.getString("phone");
                    String roleInGroup= rs.getString("roleInGroup");
                    studentDTO= new StudentDTO(fullName, studentCode, phone, email, roleInGroup);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return studentDTO;
    }
}

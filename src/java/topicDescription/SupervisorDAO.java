package topicDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class SupervisorDAO {
    public static final String ALL = "SELECT lectureName, email, phone, title FROM tblLecture";
    public static final String GET_BY_EMAIL = "SELECT lectureName, phone, title FROM tblLecture "
                                            + "WHERE email=?";

    public List<SupervisorDTO> getListSupervisor() throws SQLException {
        List<SupervisorDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(ALL);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String lectureName= rs.getString("lectureName");
                    String email= rs.getString("email");
                    String phone= rs.getString("phone");
                    String title= rs.getString("title");
                    list.add(new SupervisorDTO(lectureName, phone, email, title));
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
    
    public SupervisorDTO getSupervisorByEmail(String email) throws SQLException {
        SupervisorDTO supervisorDTO = null;
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
                    String lectureName= rs.getString("lectureName");
                    String phone= rs.getString("phone");
                    String title= rs.getString("title");
                    supervisorDTO= new SupervisorDTO(lectureName, phone, email, title);
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
        return supervisorDTO;
    }
}

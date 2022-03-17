package topicDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class SpecialDAO {
    public static final String ALL_PROFESSION = "SELECT DISTINCT profession FROM tblSpecial";
    public static final String ALL_SPECIAL = "SELECT specialID FROM tblSpecial";

    public List<String> getListProfession() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(ALL_PROFESSION);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String profession= rs.getString("profession");
                    list.add(profession);
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
    
    public List<String> getListSpecial() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(ALL_SPECIAL);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String specialID= rs.getString("specialID");
                    list.add(specialID);
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
}

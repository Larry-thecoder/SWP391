package topicDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DBUtils;

public class SelectionCriteriaDAO {
    public static final String GET_DETAILS = "SELECT details FROM tblSelectionCriteria "
                                            + "WHERE profession=?";
    public static final String INSERT = "INSERT INTO tblSelectionCriteria(profession, details) "
                                      + "VALUES(?,?)";

    public String getDetails(String profession) throws SQLException {
        String details = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(GET_DETAILS);
                pst.setString(1, profession);
                rs= pst.executeQuery();
                if(rs.next()) {
                    details= rs.getString("details");
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
        return details;
    }
    
    public boolean insert(String profession, String details) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(INSERT);
                pst.setString(1, profession);
                pst.setString(2, details);
                check= pst.executeUpdate()>0? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                pst.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return check;
    }
}

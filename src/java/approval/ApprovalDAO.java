package approval;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class ApprovalDAO {
    public static final String REJECT = "INSERT INTO tblApproval(approvalID, approverID, subjectDescriptionID, comment, isSeen) "
                                      + "VALUES(?,?,?,?,?)";
    public static final String APPROVE = "INSERT INTO tblApproval(approvalID, approverID, subjectDescriptionID) "
                                       + "VALUES(?,?,?)";
    public static final String GET_CMT = "SELECT comment FROM tblApproval "
                                       + "WHERE subjectDescriptionID=? AND isSeen=?";
    public static final String UPDATE_SEEN_CMT = "UPDATE tblApproval SET isSeen=? "
                                               + "WHERE subjectDescriptionID=?";
    public static final String COUNT_APPROVE= "SELECT COUNT(approvalID) AS countApproval "
                                            + "FROM tblApproval "
                                            + "WHERE subjectDescriptionID=? AND comment IS NULL";
    public static final String COUNT_REJECT= "SELECT COUNT(approvalID) AS countApproval "
                                           + "FROM tblApproval "
                                           + "WHERE subjectDescriptionID=? AND isSeen=?";

    public boolean reject(String approvalID, String approverID, String topicDesID, String comment) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(REJECT);
                pst.setString(1, approvalID);
                pst.setString(2, approverID);
                pst.setString(3, topicDesID);
                pst.setString(4, comment);
                pst.setInt(5, 0);
                check= pst.executeUpdate() > 0? true : false;
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
    
    public boolean approve(String approvalID, String approverID, String topicDesID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(APPROVE);
                pst.setString(1, approvalID);
                pst.setString(2, approverID);
                pst.setString(3, topicDesID);
                check= pst.executeUpdate() > 0? true : false;
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
    
    public List<String> getComments(String topicDescrID) throws SQLException {
        List<String> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(GET_CMT);
                pst.setString(1, topicDescrID);
                pst.setInt(2, 0);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String comment= rs.getString("comment");
                    list.add(comment);
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
    
    public boolean updateSeenCmt(String topicDescrID) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(UPDATE_SEEN_CMT);
                pst.setInt(1, 1);
                pst.setString(2, topicDescrID);
                check= pst.executeUpdate() > 0? true : false;
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
    
    public int countApprove(String topicDescrID) throws SQLException {
        int countApprove = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(COUNT_APPROVE);
                pst.setString(1, topicDescrID);
                rs= pst.executeQuery();
                if(rs.next()) {                    
                    countApprove= rs.getInt("countApproval");
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
        return countApprove;
    }
    
    public int countReject(String topicDescrID) throws SQLException {
        int countReject = 0;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(COUNT_REJECT);
                pst.setString(1, topicDescrID);
                pst.setInt(2, 0);
                rs= pst.executeQuery();
                if(rs.next()) {                    
                    countReject= rs.getInt("countApproval");
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
        return countReject;
    }
}

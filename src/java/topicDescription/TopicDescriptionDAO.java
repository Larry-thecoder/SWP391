package topicDescription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class TopicDescriptionDAO {
    public static final String SEARCH_BY_ID = "SELECT descriptionID, details, descriptionStatus FROM tblSD "
                                            + "WHERE descriptionID LIKE ?";
    public static final String SEARCH_BY_STATUS = "SELECT descriptionID, details, descriptionStatus FROM tblSD "
                                                + "WHERE descriptionStatus=?";
    public static final String SEARCH_BY_LECTURER_ID = "SELECT descriptionID, details, descriptionStatus FROM tblSD "
                                                     + "WHERE topicID IN "
                                                     + "(SELECT subjectID FROM tblSubject "
                                                     + "WHERE lectureID=?)";
    public static final String DELETE = "DELETE tblSD "
                                      + "WHERE descriptionID=?";
    public static final String UPDATE = "UPDATE tblSD SET details=?, descriptionStatus=? "
                                      + "WHERE descriptionID=?";
    public static final String APPROVE_OR_REJECT = "UPDATE tblSD SET descriptionStatus=? "
                                                 + "WHERE descriptionID=?";
    public static final String GET_TP_INFO = "SELECT details, descriptionStatus FROM tblSD "
                                           + "WHERE descriptionID=?";
    public static final String CHECK_DUPLICATE = "SELECT topicName FROM tblTopics "
                                               + "WHERE topicName=?";
    public static final String INSERT = "INSERT INTO tblSD(descriptionID, details, descriptionStatus) "
                                      + "VALUES(?,?,?)";

    public boolean insert(TopicDescriptionDTO TP) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(INSERT);
                pst.setString(1, TP.getTopicDescrID());
                pst.setString(2, TP.getDetails());
                pst.setString(3, TP.getTopicDescrStatus());
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
    
    public boolean checkDuplicate(String userID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(CHECK_DUPLICATE);
                pst.setString(1, userID);
                rs= pst.executeQuery();
                if (rs.next()) {
                    check= true;
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

        return check;
    }
    
    public TopicDescriptionDTO getTopicDesByID(String id) throws SQLException {
        TopicDescriptionDTO topicDes = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(SEARCH_BY_ID);
                pst.setString(1, "%"+ id +"%");
                rs= pst.executeQuery();
                if(rs.next()) {
                    String details= rs.getString("details");
                    String descriptionStatus= rs.getString("descriptionStatus");
                    topicDes= new TopicDescriptionDTO(id, details, descriptionStatus);
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
        return topicDes;
    }

    public boolean delete(String descriptionID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(DELETE);
                pst.setString(1, descriptionID);
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

    public boolean update(TopicDescriptionDTO topicDescr) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(UPDATE);
                pst.setString(1, topicDescr.getDetails());
                pst.setString(2, topicDescr.getTopicDescrStatus());
                pst.setString(3, topicDescr.getTopicDescrID());
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
    
    public boolean approveOrReject(String topicDesID, String status) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(APPROVE_OR_REJECT);
                pst.setString(1, status);
                pst.setString(2, topicDesID);
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

    public TopicDescriptionDTO getTPInfo(String descriptionID) throws SQLException {
        TopicDescriptionDTO TP= null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(GET_TP_INFO);
                pst.setString(1, descriptionID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String details= rs.getString("details");
                    String descriptionStatus= rs.getString("descriptionStatus");
                    TP = new TopicDescriptionDTO(descriptionID, details, descriptionStatus);
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
        
        return TP;
    }

    public List<TopicDescriptionDTO> getListTopicDescriptionByStatus(String status) throws SQLException {
        List<TopicDescriptionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(SEARCH_BY_STATUS);
                pst.setString(1, status);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String descriptionID= rs.getString("descriptionID");
                    String details= rs.getString("details");
                    String descriptionStatus= rs.getString("descriptionStatus");
                    list.add(new TopicDescriptionDTO(descriptionID, details, descriptionStatus));
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
    
    public List<TopicDescriptionDTO> getListTopicDescription(String lecturerID) throws SQLException {
        List<TopicDescriptionDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(SEARCH_BY_LECTURER_ID);
                pst.setString(1, lecturerID);
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String descriptionID= rs.getString("descriptionID");
                    String details= rs.getString("details");
                    String descriptionStatus= rs.getString("descriptionStatus");
                    list.add(new TopicDescriptionDTO(descriptionID, details, descriptionStatus));
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

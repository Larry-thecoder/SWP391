package topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

public class TopicDAO {
    public static final String SEARCH = "SELECT subjectID, subjectName, lectureID FROM tblSubject "
                                      + "WHERE subjectName LIKE ?";
    public static final String DELETE = "DELETE tblSubject "
                                      + "WHERE subjectID=?";
    public static final String UPDATE = "UPDATE tblSubject SET subjectName=?, lectureID=? "
                                      + "WHERE subjectID=?";
    public static final String GET_TOPIC_INFO = "SELECT subjectName, lectureID FROM tblSubject "
                                              + "WHERE subjectID=?";
    public static final String CHECK_DUPLICATE = "SELECT topicName FROM tblSubject "
                                               + "WHERE topicName=?";
    public static final String INSERT = "INSERT INTO tblSubject(subjectID, subjectName, lectureID) "
                                      + "VALUES(?,?,?,?)";
    
    public boolean insert(TopicDTO topic) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(INSERT);
                pst.setString(1, topic.getTopicID());
                pst.setString(2, topic.getTopicName());
                pst.setString(3, topic.getLecturerID());
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

    public List<TopicDTO> getListTopic(String search) throws SQLException {
        List<TopicDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst= conn.prepareStatement(SEARCH);
                pst.setString(1, "%"+ search +"%");
                rs= pst.executeQuery();
                while (rs.next()) {                    
                    String subjectID= rs.getString("subjectID");
                    String subjectName= rs.getString("subjectName");
                    String lectureID= rs.getString("lectureID");
                    list.add(new TopicDTO(subjectID, subjectName, lectureID));
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

    public boolean delete(String subjectID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(DELETE);
                pst.setString(1, subjectID);
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

    public boolean update(TopicDTO topic) throws SQLException {
        boolean check= false;
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(UPDATE);
                pst.setString(1, topic.getTopicName());
                pst.setString(2, topic.getLecturerID());
                pst.setString(3, topic.getTopicID());
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

    public TopicDTO getTopicInfo(String subjectID) throws SQLException {
        TopicDTO topic= null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                pst = conn.prepareStatement(GET_TOPIC_INFO);
                pst.setString(1, subjectID);
                rs = pst.executeQuery();
                if (rs.next()) {
                    String subjectName= rs.getString("subjectName");
                    String lectureID= rs.getString("lectureID");
                    topic = new TopicDTO(subjectID, subjectName, lectureID);
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
        
        return topic;
    }
}

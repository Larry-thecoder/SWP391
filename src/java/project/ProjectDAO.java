/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author Mr.Khuong
 */
public class ProjectDAO {
    public List<ProjectDTO> getListProject(String search) throws SQLException {
        List<ProjectDTO> listProject = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " SELECT projectID, projectName, lectureID, approverID, subjectID, status, submitTime FROM tblProject WHERE projectName like ? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, "%" + search + "%");
                rs = stm.executeQuery();
                while (rs.next()) {
                    String projectID = rs.getString("projectID");
                    String projectName = rs.getString("projectName");
                    String lectureID = rs.getString("lectureID");
                    String approverID = rs.getString("approverID");
                    String subjectID = rs.getString("subjectID");
                    String status = rs.getString("status");
                    Timestamp submitTime = rs.getTimestamp("submitTime");
                    listProject.add(new ProjectDTO(projectID, projectName, lectureID, approverID, subjectID, status, submitTime));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return listProject;
    }
    
    public boolean update(ProjectDTO project) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = " UPDATE tblProject SET approverID=?, status=? WHERE projectID=? ";
                stm = conn.prepareStatement(sql);
                stm.setString(1, project.getApproverID());
                stm.setString(2, project.getStatus());
                stm.setString(3, project.getProjectID());
                check = stm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return check;
    }
}

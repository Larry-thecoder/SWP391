/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Timestamp;

/**
 *
 * @author Mr.Khuong
 */
public class ProjectDTO {
    private String projectID;
    private String projectName;
    private String lectureID;
    private String approverID;
    private String subjectID;
    private String status;
    private Timestamp submitTime;

    public ProjectDTO() {
    }

    public ProjectDTO(String projectID, String projectName, String lectureID, String approverID, String subjectID, String status, Timestamp submitTime) {
        this.projectID = projectID;
        this.projectName = projectName;
        this.lectureID = lectureID;
        this.approverID = approverID;
        this.subjectID = subjectID;
        this.status = status;
        this.submitTime = submitTime;
    }

    public ProjectDTO(String projectID, String approverID, String status) {
        this.projectID = projectID;
        this.approverID = approverID;
        this.status = status;
    }

    
    
    

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getLectureID() {
        return lectureID;
    }

    public void setLectureID(String lectureID) {
        this.lectureID = lectureID;
    }

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        this.approverID = approverID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
        this.submitTime = submitTime;
    }
    
    
}

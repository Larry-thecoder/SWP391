/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicDescription;

/**
 *
 * @author Mr.Khuong
 */
public class TopicDescriptionError {
    private String topicDescrID;
    private String approverID;
    private String details;
    private String topicDescrStatus;

    public TopicDescriptionError() {
    }

    public TopicDescriptionError(String topicDescrID, String approverID, String details, String topicDescrStatus) {
        this.topicDescrID = topicDescrID;
        this.approverID = approverID;
        this.details = details;
        this.topicDescrStatus = topicDescrStatus;
    }

    public String getTopicDescrID() {
        return topicDescrID;
    }

    public void setTopicDescrID(String topicDescrID) {
        this.topicDescrID = topicDescrID;
    }

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        this.approverID = approverID;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getTopicDescrStatus() {
        return topicDescrStatus;
    }

    public void setTopicDescrStatus(String topicDescrStatus) {
        this.topicDescrStatus = topicDescrStatus;
    }
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topic;

/**
 *
 * @author Mr.Khuong
 */
public class TopicError {
    private String topicID;
    private String topicName;
    private String descriptionID;
    private String lecturerID;

    public TopicError() {
    }

    public TopicError(String topicID, String topicName, String descriptionID, String lecturerID) {
        this.topicID = topicID;
        this.topicName = topicName;
        this.descriptionID = descriptionID;
        this.lecturerID = lecturerID;
    }

    public String getTopicID() {
        return topicID;
    }

    public void setTopicID(String topicID) {
        this.topicID = topicID;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getDescriptionID() {
        return descriptionID;
    }

    public void setDescriptionID(String descriptionID) {
        this.descriptionID = descriptionID;
    }

    public String getLecturerID() {
        return lecturerID;
    }

    public void setLecturerID(String lecturerID) {
        this.lecturerID = lecturerID;
    }
    
    
}

package topic;

public class TopicDTO {
    private String topicID;
    private String topicName;
    private String descriptionID;
    private String lecturerID;

    public TopicDTO() {
    }

    public TopicDTO(String topicID, String topicName, String descriptionID, String lecturerID) {
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

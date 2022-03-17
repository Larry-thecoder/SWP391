package topicDescription;

public class TopicDescriptionDTO {
    private String topicDescrID;
    private String details;
    private String topicDescrStatus;

    public TopicDescriptionDTO() {
    }
    
    public TopicDescriptionDTO(String topicDescrID, String details, String topicDescrStatus) {
        this.topicDescrID = topicDescrID;
        this.details = details;
        this.topicDescrStatus = topicDescrStatus;
    }

    public String getTopicDescrID() {
        return topicDescrID;
    }

    public void setTopicDescrID(String topicDescrID) {
        this.topicDescrID = topicDescrID;
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

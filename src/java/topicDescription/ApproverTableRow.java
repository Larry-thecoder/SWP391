package topicDescription;

public class ApproverTableRow {
    private String topicDescrID;
    private String fileName;
    private String profession;

    public ApproverTableRow() {
    }

    public ApproverTableRow(String topicDescrID, String fileName, String profession) {
        this.topicDescrID = topicDescrID;
        this.fileName = fileName;
        this.profession = profession;
    }

    public String getTopicDescrID() {
        return topicDescrID;
    }

    public void setTopicDescrID(String topicDescrID) {
        this.topicDescrID = topicDescrID;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}

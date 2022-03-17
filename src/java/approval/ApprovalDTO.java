package approval;

public class ApprovalDTO {
    private String approvalID;
    private String approverID;
    private String subjectDescriptionID;
    private String comment;

    public ApprovalDTO() {
    }

    public ApprovalDTO(String approvalID, String approverID, String subjectDescriptionID, String comment) {
        this.approvalID = approvalID;
        this.approverID = approverID;
        this.subjectDescriptionID = subjectDescriptionID;
        this.comment = comment;
    }

    public String getApprovalID() {
        return approvalID;
    }

    public void setApprovalID(String approvalID) {
        this.approvalID = approvalID;
    }

    public String getApproverID() {
        return approverID;
    }

    public void setApproverID(String approverID) {
        this.approverID = approverID;
    }

    public String getSubjectDescriptionID() {
        return subjectDescriptionID;
    }

    public void setSubjectDescriptionID(String subjectDescriptionID) {
        this.subjectDescriptionID = subjectDescriptionID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

package topicDescription;

import java.util.List;

public class TopicDescriptionDetails {
    private String className;
    private List<String> durationTime;
    private String profession;
    private List<String> special;
    private String kindOfPersonMakeRegisters;
    private List<SupervisorDTO> supervisors;
    private List<StudentDTO> students;
    
    private String thesisNameEnglish;
    private String thesisNameVN;
    private String thesisNameAbbr;
    
    private String mainContent_Theory;
    private String mainContent_Practice;
    
    private String otherComment;
    
    private String signingPlace;
    private String signedDate;

    public TopicDescriptionDetails() {
    }

    public TopicDescriptionDetails(String className, List<String> durationTime, String profession, List<String> special, String kindOfPersonMakeRegisters, List<SupervisorDTO> supervisors, List<StudentDTO> students, String thesisNameEnglish, String thesisNameVN, String thesisNameAbbr, String mainContent_Theory, String mainContent_Practice, String otherComment, String signingPlace, String signedDate) {
        this.className = className;
        this.durationTime = durationTime;
        this.profession = profession;
        this.special = special;
        this.kindOfPersonMakeRegisters = kindOfPersonMakeRegisters;
        this.supervisors = supervisors;
        this.students = students;
        this.thesisNameEnglish = thesisNameEnglish;
        this.thesisNameVN = thesisNameVN;
        this.thesisNameAbbr = thesisNameAbbr;
        this.mainContent_Theory = mainContent_Theory;
        this.mainContent_Practice = mainContent_Practice;
        this.otherComment = otherComment;
        this.signingPlace = signingPlace;
        this.signedDate = signedDate;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(List<String> durationTime) {
        this.durationTime = durationTime;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public List<String> getSpecial() {
        return special;
    }

    public void setSpecial(List<String> special) {
        this.special = special;
    }

    public String getKindOfPersonMakeRegisters() {
        return kindOfPersonMakeRegisters;
    }

    public void setKindOfPersonMakeRegisters(String kindOfPersonMakeRegisters) {
        this.kindOfPersonMakeRegisters = kindOfPersonMakeRegisters;
    }

    public List<SupervisorDTO> getSupervisors() {
        return supervisors;
    }

    public void setSupervisors(List<SupervisorDTO> supervisors) {
        this.supervisors = supervisors;
    }

    public List<StudentDTO> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDTO> students) {
        this.students = students;
    }

    public String getThesisNameEnglish() {
        return thesisNameEnglish;
    }

    public void setThesisNameEnglish(String thesisNameEnglish) {
        this.thesisNameEnglish = thesisNameEnglish;
    }

    public String getThesisNameVN() {
        return thesisNameVN;
    }

    public void setThesisNameVN(String thesisNameVN) {
        this.thesisNameVN = thesisNameVN;
    }

    public String getThesisNameAbbr() {
        return thesisNameAbbr;
    }

    public void setThesisNameAbbr(String thesisNameAbbr) {
        this.thesisNameAbbr = thesisNameAbbr;
    }

    public String getMainContent_Theory() {
        return mainContent_Theory;
    }

    public void setMainContent_Theory(String mainContent_Theory) {
        this.mainContent_Theory = mainContent_Theory;
    }

    public String getMainContent_Practice() {
        return mainContent_Practice;
    }

    public void setMainContent_Practice(String mainContent_Practice) {
        this.mainContent_Practice = mainContent_Practice;
    }

    public String getOtherComment() {
        return otherComment;
    }

    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment;
    }

    public String getSigningPlace() {
        return signingPlace;
    }

    public void setSigningPlace(String signingPlace) {
        this.signingPlace = signingPlace;
    }

    public String getSignedDate() {
        return signedDate;
    }

    public void setSignedDate(String signedDate) {
        this.signedDate = signedDate;
    }
}

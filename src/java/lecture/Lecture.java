/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lecture;

/**
 *
 * @author monsh
 */
public class Lecture {
   
    private String LectureID;
    private String LectureName;
    private String major;
    private String AccountID;

    public Lecture() {
    }

    public Lecture(String LectureID, String LectureName, String major, String AccountID) {
        this.LectureID = LectureID;
        this.LectureName = LectureName;
        this.major = major;
        this.AccountID = AccountID;
    }

    /**
     * Get the value of AccountID
     *
     * @return the value of AccountID
     */
    public String getAccountID() {
        return AccountID;
    }

    /**
     * Set the value of AccountID
     *
     * @param AccountID new value of AccountID
     */
    public void setAccountID(String AccountID) {
        this.AccountID = AccountID;
    }
    

    /**
     * Get the value of major
     *
     * @return the value of major
     */
    public String getMajor() {
        return major;
    }

    /**
     * Set the value of major
     *
     * @param major new value of major
     */
    public void setMajor(String major) {
        this.major = major;
    }


    /**
     * Get the value of LectureName
     *
     * @return the value of LectureName
     */
    public String getLectureName() {
        return LectureName;
    }

    /**
     * Set the value of LectureName
     *
     * @param LectureName new value of LectureName
     */
    public void setLectureName(String LectureName) {
        this.LectureName = LectureName;
    }

    /**
     * Get the value of LectureID
     *
     * @return the value of LectureID
     */
    public String getLectureID() {
        return LectureID;
    }

    /**
     * Set the value of LectureID
     *
     * @param LectureID new value of LectureID
     */
    public void setLectureID(String LectureID) {
        this.LectureID = LectureID;
    }
 
}

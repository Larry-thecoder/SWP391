package topicDescription;

public class StudentDTO {
    private String fullName;
    private String studentCode;
    private String phone;
    private String email;
    private String roleInGroup;

    public StudentDTO() {
    }

    public StudentDTO(String fullName, String studentCode, String phone, String email, String roleInGroup) {
        this.fullName = fullName;
        this.studentCode = studentCode;
        this.phone = phone;
        this.email = email;
        this.roleInGroup = roleInGroup;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getStudentCode() {
        return studentCode;
    }

    public void setStudentCode(String studentCode) {
        this.studentCode = studentCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleInGroup() {
        return roleInGroup;
    }

    public void setRoleInGroup(String roleInGroup) {
        this.roleInGroup = roleInGroup;
    }
}

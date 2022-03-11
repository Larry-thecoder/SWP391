package topicDescription;

public class Supervisor {
    private String fullName;
    private String phone;
    private String email;
    private String title;

    public Supervisor() {
    }

    public Supervisor(String fullName, String phone, String email, String title) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.title = title;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

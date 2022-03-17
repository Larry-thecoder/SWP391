package account;

public class AccountDTO {
    private String accountID;
    private String userName;
    private String password;
    private String role;

    public AccountDTO() {
    }
    
    
    public AccountDTO(String accountID, String userName, String password, String role) {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    
}

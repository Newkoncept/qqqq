package Model;

import java.io.Serializable;

public class Person implements Serializable {
    private String userId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String createdDate;
    private static final long serialVersionUID = 6529685098267757690L;

    // class construtor
    public Person(String userId, String firstName, String lastName, String emailAddress,
            String password, String createdDate) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.password = password;
        this.createdDate = createdDate;
    }

    /**
     * 
     * getter and setters for variables
     */
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}

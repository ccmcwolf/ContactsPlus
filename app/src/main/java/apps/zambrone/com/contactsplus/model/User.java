package apps.zambrone.com.contactsplus.model;

import java.util.Date;

/**
 * Created by Chamith on 29/12/2016.
 */

public class User {


    private String userID;
    private String userName;
    private String email;
    private String phoneNumber;
    private String gender;
    private java.util.Date birthday;
    private String propicPath;

    public User() {
    }

    public User(String userID, String userName, String email, String phoneNumber, String gender, Date birthday, String propicPath) {
        this.userID = userID;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.propicPath = propicPath;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPropicPath() {
        return propicPath;
    }

    public void setPropicPath(String propicPath) {
        this.propicPath = propicPath;
    }
}

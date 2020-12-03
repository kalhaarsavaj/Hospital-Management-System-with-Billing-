/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem.models;

import java.util.ArrayList;

/**
 *
 * @author NIAR Tech
 */
public class UserRecordModel {

    private String fname = "";
    private String lname = "";
    private String email = "";
    private String pwd = "";
    private String dob = "";
    private String phoneNo = "";
    private String gender = "";
    private String userType = "";

    public UserRecordModel(ArrayList<String> dataObj) {
        this.fname = dataObj.get(0);
        this.lname = dataObj.get(1);
        this.email = dataObj.get(2);
        this.pwd = dataObj.get(3);
        this.dob = dataObj.get(4);
        this.phoneNo = dataObj.get(5);
        this.gender = dataObj.get(6);
        this.userType = dataObj.get(7);
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

}

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
public class PatientRecordModel {
    private String username;
    private String bloodSugar;
    private String heartBeats;
    private String weight;
    private String symptoms;
    private String prescription;
    private String date;
    
    public PatientRecordModel(ArrayList<String> dataObj){
    this.username = dataObj.get(0);
    this.bloodSugar = dataObj.get(1);
    this.heartBeats = dataObj.get(2);
    this.weight = dataObj.get(3);
    this.symptoms = dataObj.get(4);
    this.prescription = dataObj.get(5);
    this.date = dataObj.get(6);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(String bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public String getHeartBeats() {
        return heartBeats;
    }

    public void setHeartBeats(String heartBeats) {
        this.heartBeats = heartBeats;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getPrescription() {
        return prescription;
    }

    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    
    
}

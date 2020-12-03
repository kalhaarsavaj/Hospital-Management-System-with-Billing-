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
public class ScheduleModel {
     private String timeId = "";
    private String username = "";
    private String timing = "";
    public ScheduleModel(ArrayList<String> dataObj){
        this.timeId = dataObj.get(0);
        this.username = dataObj.get(1);
        this.timing = dataObj.get(2);
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTiming() {
        return timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
    
    

}

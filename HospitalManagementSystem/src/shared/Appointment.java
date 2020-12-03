/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_CYAN_BACKGROUND;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_GREEN;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RED;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RESET;
import hospitalmanagementsystem.models.ScheduleModel;
import hospitalmanagementsystem.models.PatientRecordModel;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author NIAR Tech
 */
public class Appointment {
    
    public ArrayList<String> fetchAvailableSlot() {
        ArrayList<String> slots = new ArrayList<>();
        //pallavi fetch data from excel and add in slots
        slots.add("09:00:00 to 10:00:00");
        slots.add("10:00:00 to 11:00:00");
        slots.add("11:00:00 to 12:00:00");
        slots.add("12:00:00 to 13:00:00");
        slots.add("14:00:00 to 15:00:00");
        slots.add("15:00:00 to 16:00:00");
        slots.add("16:00:00 to 17:00:00");
        slots.add("17:00:00 to 18:00:00");
        slots.add("18:00:00 to 19:00:00");
        slots.add("19:00:00 to 20:00:00");
        slots.add("20:00:00 to 21:00:00");
        return slots;
    }

   public void setAppointment(String appointment,String username,int timeId ) {
        //pallavi store data in excel file
        String[] data = new String[3];
        data[0]=Integer.toString(timeId);
        data[1]=username;
        data[2]=appointment;
        WriteToExcel writetoExcel =  new WriteToExcel();
        try{
            writetoExcel.writeData(CommonVariables.appointmentLabelsFileName,CommonVariables.appointmentLabels , data, CommonVariables.nonSignupClass);
            System.out.println("\n" + ANSI_GREEN + "Your Appoint has been booked for " + appointment + ANSI_RESET + "\n\n");
        }catch(Exception e){
            System.err.println(e);
        }
    }
    public void getPatientHistory(String username){
        System.out.println(ANSI_CYAN_BACKGROUND+"-----------------------History of patient----------------------- \n"+ANSI_RESET);
        ArrayList<PatientRecordModel> patientRecords = new ArrayList<PatientRecordModel>();
//        ArrayList<String> appointmentTime = new  ArrayList<String>();
        ReadFromExcel readClass = new ReadFromExcel();
        patientRecords = readClass.readPatientRecords("history",CommonVariables.patientRecordsFileName);
        for(int row=0; row<patientRecords.size();row++){
            if(username.equals(patientRecords.get(row).getUsername())){
            System.out.println(ANSI_RED+"Consulted Date: \t"+patientRecords.get(row).getDate()+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Enter Data for Patient: \t" + username+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Enter Blood Sugar: \t"+patientRecords.get(row).getBloodSugar()+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Heart Beats: \t"+patientRecords.get(row).getHeartBeats()+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Enter Weight: \t"+patientRecords.get(row).getWeight()+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Symptoms: \t"+patientRecords.get(row).getSymptoms()+ANSI_RESET);
            System.out.println(ANSI_GREEN+"Prescription: \t"+patientRecords.get(row).getPrescription()+ANSI_RESET);
            
            }
            System.out.println(ANSI_CYAN_BACKGROUND+"------------------------------------------------------------------ \n"+ANSI_RESET);
        }
        
    }
    public String getCurrentPatient(){
        Date date = new Date();
        int hours = date.getHours();
        String username = "";
//        int minutes = date.getMinutes();
//        System.out.println(hours+"i love to code");
        ArrayList<ScheduleModel> scheduleRecords = getAllAppointments();
//        ArrayList<String> appointmentTime = new  ArrayList<String>();
//        ReadFromExcel readClass = new ReadFromExcel();
//        scheduleRecords = readClass.readScheduleRecords("schedule",CommonVariables.appointmentLabelsFileName);
//        System.out.println(scheduleRecords.size());
        for (ScheduleModel scheduleRecord : scheduleRecords) {
            String timing = scheduleRecord.getTiming();
            String[] scheduleHour = timing.split(":");
//            System.out.println("----->"+scheduleHour[0]+"--->"+timing);
            if (Integer.parseInt(scheduleHour[0]) == hours) {
                username = scheduleRecord.getUsername();
            }
        }
        return username;
    }
    
    public  ArrayList<ScheduleModel> getAllAppointments(){
        ArrayList<ScheduleModel> scheduleRecords = new ArrayList<>();
//        ArrayList<String> appointmentTime = new  ArrayList<String>();
        ReadFromExcel readClass = new ReadFromExcel();
        scheduleRecords = readClass.readScheduleRecords("schedule",CommonVariables.appointmentLabelsFileName);
        return scheduleRecords;
    }
    public void getNextPatient(){
        //check according to appointment time and current time
         ArrayList<ScheduleModel> allAppointments= getAllAppointments();
//        ArrayList<String> appointmentDetails = new ArrayList<>();
        System.out.println("\n"+ANSI_CYAN_BACKGROUND +"Next In Line"+ ANSI_RESET);
         Date date = new Date();
         int temp = 99;
         String Username = "";
         String Timing = "";
        for (ScheduleModel nextApp : allAppointments){           
            String timing = nextApp.getTiming();
            String[] scheduleHour = timing.split(":");
            if((Integer.parseInt(scheduleHour[0]) > date.getHours()) && (Integer.parseInt(scheduleHour[0]) < temp)){
                temp = Integer.parseInt(scheduleHour[0]);
                Username=nextApp.getUsername();
                Timing = nextApp.getTiming();
            }
        }
        if(Username!=""){
            String patientDetails = "Patient User Name : "+Username+ "=====>   Booked Time Slot : "+Timing;
            System.out.println(ANSI_RED+patientDetails+ANSI_RESET+"\n");
        }else{
            System.out.println(ANSI_RED+"No patient in line"+ANSI_RESET+"\n");
        }
//        return "username";
    }
    public HashMap<String, String> getUserDetails(String patientUsername){
        // fetch all data and store in HashMap
        HashMap<String, String> userDetails = new HashMap<>();
        userDetails.put("username", "garvitpatel196");
        userDetails.put("name","Garvit "+"Patel");
        userDetails.put("phone","4372497878");
        userDetails.put("gender","M");
        return userDetails;
    }
}

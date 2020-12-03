/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import static hospitalmanagementsystem.HospitalManagementSystem.*;
import java.util.Date;
import java.util.Scanner;
import shared.Appointment;
import shared.CommonVariables;
import shared.WriteToExcel;

/**
 *
 * @author gpatel
 */
public class DoctorLogin {
    void docterDashboard(String username) {
        System.out.println("\n"+ANSI_CYAN_BACKGROUND+"Welcome " + username + " to the doctor's dashboard"+ANSI_RESET+"\n");
        boolean flag = true;
        Appointment appointment = new Appointment();
        while (flag) {
            String patientUsername = appointment.getCurrentPatient();
            if(patientUsername == ""){
            System.out.println("No patient available currently");
            }else{
                System.out.println("1. Enter data for Current Patient");
            }          
            
            System.out.println("2. Get Information of Next Patient");
            if(patientUsername != ""){
            System.out.println("3. Check history of Current Patient");
            }else{
                System.out.println("3. Check history of any Patient");
            }
            System.out.println("0. Logout");
            System.out.print("Please select any option from above list:");
            Scanner dockDashboardOptions = new Scanner(System.in);
            switch (dockDashboardOptions.nextInt()) {
                case 1:
                    if(patientUsername != ""){
                    dockDashboardOptions.nextLine();
                    setDataCurrPatient(patientUsername);
                    }
                    break;
                case 2:
                    dockDashboardOptions.nextLine();
                    appointment.getNextPatient();
                    break;
                case 3:
                    if(patientUsername != ""){
                    dockDashboardOptions.nextLine();
                    appointment.getPatientHistory(username);
                    }else{
                    System.out.println("Enter username of the patient");
                    Scanner writeUsername = new Scanner(System.in);
                    String name = writeUsername.nextLine();
                    appointment.getPatientHistory(name);
                    }
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Please select valid option.");
                    break;
            }
        }
    }
    void setDataCurrPatient(String patientUsername) {
        Scanner currPatientDataInput = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("Enter Data for Patient " + patientUsername);
            System.out.print("Enter Blood Sugar: ");
            String bloodSugar = currPatientDataInput.nextLine();
            System.out.print("Heart Beats: ");
            String heartBeats = currPatientDataInput.nextLine();
            System.out.print("Enter Weight: ");
            String weight = currPatientDataInput.nextLine();
            System.out.print("Symptoms: ");
            String symptoms = currPatientDataInput.nextLine();
            System.out.print("Prescription: ");
            String prescription= currPatientDataInput.nextLine();
           
            System.out.println("\n\n");
            System.out.println("1. Save");
            System.out.println("2. Renter data");
            System.out.println("0. Go back to main menu without saving");
            System.out.println("Please select any one option from above list: ");
            switch (currPatientDataInput.nextInt()) {
                case 1:
                    currPatientDataInput.nextLine();
                    storeData(patientUsername,bloodSugar,heartBeats,weight,symptoms,prescription);
                    System.out.println("Data is saved.");
                    flag = false;
                    break;
                case 2:
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("\n\n" +ANSI_RED+"Please enter valid option.");
                    break;
            }
            currPatientDataInput.nextLine();
        }
    }
    void storeData(String patientUsername,String bloodSugar,String heartBeats,String weight,String symptoms,String prescription ){
        //store data in excel file
        int i =0;
        Date date = new Date();
        String[] data = new String[10];
        data[i++]= patientUsername;
        data[i++]= bloodSugar;
        data[i++]= heartBeats;
        data[i++]= weight;
        data[i++]= symptoms;
        data[i++]= prescription;
        data[i++]= date.toString();
        
        WriteToExcel patientRecord = new WriteToExcel();
//                String[] dataa = {"Pal","kotvir","pallavi@gmail.com","Asdf@123","14-081995","Female","9916067559"};
                try{
                patientRecord.writeData(CommonVariables.patientRecordsFileName, CommonVariables.patientRecordsLabels, data, CommonVariables.nonSignupClass );
                }catch(Exception e){
                }
    }
}

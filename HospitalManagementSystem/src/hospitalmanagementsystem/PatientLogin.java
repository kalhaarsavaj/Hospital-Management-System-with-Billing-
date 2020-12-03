/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import static hospitalmanagementsystem.HospitalManagementSystem.*;
import hospitalmanagementsystem.models.ScheduleModel;
import java.util.ArrayList;
import java.util.Scanner;
import shared.Appointment;
import shared.CommonVariables;
import shared.ReadFromExcel;

/**
 *
 * @author gpatel
 */
public class PatientLogin {
    void patientDashboard(String username) {
        System.out.println("\n==========================================================================");
        System.out.println("\n"+ANSI_CYAN_BACKGROUND+"Welcome " + username + " to the patient's dashboard"+ANSI_RESET);
        boolean flag = true;
        while (flag) {
            System.out.println("1. Schedule New Appointment");
            System.out.println("2. Check Schedule Appointment.");
            System.out.println("3. Check History");
            System.out.println("0. Logout");
            System.out.print("Please select any option from above list:");
            Scanner patientDashboardOptions = new Scanner(System.in);
            switch (patientDashboardOptions.nextInt()) {
                case 1:
                    patientDashboardOptions.nextLine();
                    scheduleNewAppointment(username);
                    break;
                case 2:
                    ArrayList<String> appointments=getMyAppointment(username);
                    if(appointments.size()==0){
                    System.out.println("\n"+ANSI_GREEN+"You have no upcoming appointments "+ANSI_RESET);
                    }else{
                    System.out.println("\n"+ANSI_GREEN+"You have an appointment/s with doctor at "+ANSI_RESET);                   
                    for(int i=0;i<appointments.size();i++){
                        System.out.println(ANSI_GREEN+appointments.get(i)+ANSI_RESET+"\n");                    
                    }
                    }
                    break;
                case 3:
                    Appointment appointment = new Appointment();
                    appointment.getPatientHistory(username);
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
    void scheduleNewAppointment(String username){
        System.out.println("\n"+ANSI_YELLOW+"Select the slot from below available time"+ANSI_RESET);
        ArrayList<String> availableSlots;
        Appointment appointment = new Appointment();
        availableSlots = appointment.fetchAvailableSlot();
        for(int i=0; i < availableSlots.size(); i++){
            System.out.println((i+1)+". "+availableSlots.get(i));
        }
        System.out.println("0. Cancel");
        Scanner slotOption = new Scanner(System.in);
        System.out.print("Please enter option: ");
        int timeId = slotOption.nextInt();
        if(timeId != 0){
            appointment.setAppointment(availableSlots.get(timeId - 1),username,timeId);
        }
    }
    
     ArrayList<String> getMyAppointment(String patientName){
        //pallavi fetch appointment of data from excel file
        ArrayList<ScheduleModel> scheduleRecords = new ArrayList<>();
        ArrayList<String> appointmentTime = new  ArrayList<>();
        ReadFromExcel readClass = new ReadFromExcel();
        scheduleRecords = readClass.readScheduleRecords("schedule",CommonVariables.appointmentLabelsFileName);
        for (ScheduleModel scheduleRecord : scheduleRecords) {
            if (scheduleRecord.getUsername().equals(patientName)) {
                appointmentTime.add(scheduleRecord.getTiming());
            }
            System.out.println(scheduleRecord.getTiming());
        }
        return appointmentTime;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import hospitalmanagementsystem.models.Invoice;
import static hospitalmanagementsystem.HospitalManagementSystem.*;
import hospitalmanagementsystem.models.ScheduleModel;
import java.util.Date;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import shared.*;

/**
 *
 * @author gpatel
 */
public class ReceptionistLogin {

    void Dashboard(String username) {
        System.out.println("\n==========================================================================");
        System.out.println("\n" + ANSI_CYAN_BACKGROUND + "Welcome " + username + " to the Receptionist's dashboard" + ANSI_RESET);
        boolean flag = true;
        Appointment appointment = new Appointment();
        while (flag) {
            String patientUsername = appointment.getCurrentPatient();
            System.out.println("1. List all the upcoming appointments");
            System.out.println("2. Book New Appointment");
            System.out.println("3. Print the invoice of current Patient");
            System.out.println("0. Logout");
            System.out.print("Please select any option from above list:");
            Scanner receptionistDashboardOptions = new Scanner(System.in);
            switch (receptionistDashboardOptions.nextInt()) {
                case 1:
                    listAllAppointments();
                    break;
                case 2:
                    scheduleNewAppointment();
                    break;
                case 3:
                    printInvoice(patientUsername);
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

    void scheduleNewAppointment() {
        Scanner slotOption = new Scanner(System.in);
        System.out.print("Enter Patient username: ");
        String patientUsername = slotOption.nextLine();
        System.out.println("\n" + ANSI_YELLOW + "Select the slot from below available time" + ANSI_RESET);
        ArrayList<String> availableSlots;
        Appointment appointment = new Appointment();
        availableSlots = appointment.fetchAvailableSlot();
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }
        System.out.print("Please enter option: ");
        int timeId = slotOption.nextInt();
        if (timeId > 1 && timeId <= availableSlots.size()) {
            appointment.setAppointment(availableSlots.get(timeId - 1), patientUsername, timeId);
        } else {
            System.out.println("\n\n" + ANSI_RED + "Invalid option entered" + ANSI_RESET + "\n\n");
        }
    }

    void listAllAppointments() {
        //pallavi fetch appointment of data from excel file
        //required fields Fname, Lname, Gender, phno, time 
        Appointment app = new Appointment();
        ArrayList<ScheduleModel> allAppointments = app.getAllAppointments();
//        ArrayList<String> appointmentDetails = new ArrayList<>();
        System.out.println("\n" + ANSI_CYAN_BACKGROUND + "Upcoming appointments" + ANSI_RESET + "\n");
        for (int row = 0; row < allAppointments.size(); row++) {
            Date date = new Date();
            String timing = allAppointments.get(row).getTiming();
            String[] scheduleHour = timing.split(":");
            if (date.getHours() <= Integer.parseInt(scheduleHour[0])) {
                System.out.println(ANSI_GREEN + "Patient Name:" + allAppointments.get(row).getUsername() + "--->" + "Timing:" + allAppointments.get(row).getTiming() + ANSI_RESET + "\n");
            } else {
                if (row == allAppointments.size() - 1) {
                    System.out.println(ANSI_RED + "No upcoming appointments" + ANSI_RESET + "\n");
                }
            }
        }
    }

    void printInvoice(String patientUsername) {
        Scanner invoiceScanner = new Scanner(System.in);
        System.out.println("Please enter appointment charge:");
        float appointmentCharges = Float.parseFloat(invoiceScanner.nextLine());
        System.out.println("Please enter Medicine charge:");
        float medicineCharges = Float.parseFloat(invoiceScanner.nextLine());
//        invoiceScanner.nextLine();
        System.out.println("Please enter Payment method (card/cash):");
        String paymentMethod = invoiceScanner.nextLine();

        float GST = (appointmentCharges * 13) / 100;
        float total = appointmentCharges + medicineCharges + GST;
        Appointment appointment = new Appointment();
        HashMap<String, String> userDetails = appointment.getUserDetails(patientUsername);

        //Invoice invoice = new Invoice("John","Frost",22,"Med1",250,"Credit Card");
        Invoice invoice = new Invoice(userDetails.get("name"), userDetails.get("phone"), userDetails.get("gender"), appointmentCharges, medicineCharges, GST, total, paymentMethod);
        ConvertToPDF covertToPdf = new ConvertToPDF();
        covertToPdf.convertObjToPDF(invoice);
    }

//    void WriteObjectToFile(Object serObj) {
//        String filepath = "./data/invoice.txt";
//        try {
//
//            FileOutputStream fileOut = new FileOutputStream(filepath, true);
////            FileWriter fileWriter = new FileWriter("c:/temp/samplefile.txt", true); //Set true for append mode
////            PrintWriter printWriter = new PrintWriter(fileWriter);
//            try (ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
//                objectOut.writeObject(serObj.toString());
//            }
//            System.out.println("The Object  was succesfully written to a file");
//
//        } catch (Exception ex) {
//            System.out.print(ex + "\n");
//        }
//    }

    
}

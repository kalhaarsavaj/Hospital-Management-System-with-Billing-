/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_CYAN_BACKGROUND;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RESET;
import static hospitalmanagementsystem.HospitalManagementSystem.ANSI_RED;
import hospitalmanagementsystem.models.UserRecordModel;
import java.util.ArrayList;
import java.util.Scanner;
import shared.CommonVariables;
import shared.EncryptionDecryptionAES;
import shared.ReadFromExcel;
import shared.WriteToExcel;

/**
 *
 * @author gpatel
 */
public class HospitalManagementSystem {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        HospitalManagementSystem hs = new HospitalManagementSystem();
        hs.mainMenu();
    }

    public void mainMenu(){
        Scanner option = new Scanner(System.in);
        boolean flag = true;
        while (flag) {
            System.out.println("\n==========================================================================");
            System.out.println("\n"+ANSI_CYAN_BACKGROUND+"Welcome to Hospital Management System...!"+ANSI_RESET);
            System.out.println("1. SignUp");
            System.out.println("2. Login");
            System.out.println("0. Exit");
            System.out.print("Please select any option from above list:");
            switch (option.nextInt()) {
                case 1:
                    SignUp signupObj = new SignUp();
                    signupObj.signUpMenu();
                    break;
                case 2:
                    LoginMenu menu = new LoginMenu();
                    menu.loginMenu();
                    break;
                case 0:
                    System.exit(0);
                    break;
//                case 3:
//                WriteToExcel writeMethod = new WriteToExcel();
//                try{
//                    String[] labels = {"fname","lname","email","pwd","DOB","gender","phoneNo"};
//                    String[] data = {"Pal","kotvir","pallavi@gmail.com","Asdf@123","14-081995","Female","9916067559"};
//                    writeMethod.writeData("output.xls",labels,data);
//                }catch(Exception e){
//                    System.out.println(e);
//                }
                default:
                    System.out.println("Please enter valid input \n \n");
                    break;
            }
        }
    }
}

class LoginMenu{
    String userType;
    int CounterLoginAttempt = 0;
    void loginMenu() {
        EncryptionDecryptionAES crypto = new EncryptionDecryptionAES();
        Scanner loginOption = new Scanner(System.in);
        System.out.println("\n==========================================================================");
        System.out.println("\n"+ANSI_CYAN_BACKGROUND+"Welcome to Login Menu"+ANSI_RESET);
        System.out.print("Please Enter your Username: ");
        String username = loginOption.nextLine();
        System.out.print("Please Enter your Password: ");
        String password = loginOption.nextLine();
        password = crypto.encrypt(password);

        if(isValidLoginCredentials(username,password)){
            System.out.println("Login Successfull");
            System.out.println("Redirecting to your Dashboard"+userType);
                switch (userType) {
                case "doctor":
                    DoctorLogin doctorObj = new DoctorLogin();
                    doctorObj.docterDashboard(username);
                    break;
                case "patient":
                    PatientLogin patientObj = new PatientLogin();
                    patientObj.patientDashboard(username);
                    System.out.println("Welcome to patient's dashboard \n \n");
                    break;
                case "receptionist":
                    ReceptionistLogin receptionistObj = new ReceptionistLogin();
                    receptionistObj.Dashboard(username);
                    System.out.println("Welcome to receptionist dashboard \n \n");
                    break;
            }

        }else{            
            System.out.println("\n"+ANSI_RED+"Invalid credetials"+ANSI_RESET);
            System.out.println(ANSI_RED+"Redirecting back to login menu. Retry entering username and password"+ANSI_RESET);
            while(CounterLoginAttempt<=2){
                CounterLoginAttempt++;
//                System.out.println(CounterLoginAttempt+"fbcbnbncnfddfnn");
                loginMenu();                
            }
            HospitalManagementSystem menu = new HospitalManagementSystem();
            menu.mainMenu();
        }
    }
    boolean isValidLoginCredentials(String username, String password){
        //pallavi fetch data from excel and validate credentials.
        boolean validateUserFlag = false;
        ArrayList<UserRecordModel> userRecords = new ArrayList<UserRecordModel>();
        ReadFromExcel readClass = new ReadFromExcel();
        userRecords = readClass.readUserRecord("signup",CommonVariables.userRecordFileName);
        if(userRecords.size()!=0){
        for(int row = 0; row < userRecords.size(); row++){
            if(username.equals(userRecords.get(row).getEmail()) && password.equals(userRecords.get(row).getPwd())){
            validateUserFlag = true;
//            System.out.println(userRecords.get(row).getUserType()+"-----ffgffgg------------");
             userType = userRecords.get(row).getUserType();
//             System.out.println(">>>>>>>>>>>>>>"+userRecords.get(row).getEmail());
             break;
            }else{
//                System.out.println(userRecords.get(row).getEmail()+"-----ffgffgg------------"+userRecords.size());
            validateUserFlag = false;
            }
            System.out.println("\n==========================================================================");
        }
        }       
        return validateUserFlag;
    }
}
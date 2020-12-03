/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospitalmanagementsystem;

import static hospitalmanagementsystem.HospitalManagementSystem.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import shared.EncryptionDecryptionAES;
import shared.CommonVariables;
import shared.WriteToExcel;
/**
 *
 * @author gpatel
 */
public class SignUp {
    String msg = "Something went wrong";
    String[] data = new String[9];
    void signUpMenu() {
        System.out.println("\n\n==========================================================================");
        System.out.println("\n\n"+ANSI_CYAN_BACKGROUND+"Welcome to Sign Up Menu\n \n"+ANSI_RESET);
        Scanner signUpOption = new Scanner(System.in);
        boolean signUpLoop = true;
        EncryptionDecryptionAES crypto = new EncryptionDecryptionAES();
        
        while(signUpLoop){
            int counter = 0;
            System.out.print("Please Enter your First Name: ");
            String fName = signUpOption.nextLine();
            data[counter++] = fName;
            System.out.print("Please Enter your Last Name: ");
            String lName = signUpOption.nextLine();
            data[counter++] = lName;
            System.out.print("Please Enter your Email ID: ");
            String email = signUpOption.nextLine().toLowerCase();
            data[counter++] = email;
//            System.out.print("Please Enter your Username: ");
//            String username = signUpOption.nextLine();
//            data[counter++] = username;
            System.out.print("Please Enter your Password: ");
            String password = signUpOption.nextLine();  
            password = crypto.encrypt(password);
            data[counter++] =password;
            System.out.print("Please Confirm your Password: ");
            String confirmPassword = signUpOption.nextLine();
            confirmPassword = crypto.encrypt(confirmPassword);
//            data[counter++] = confirmPassword;
            System.out.print("Please Enter your Phone number: ");
            String phno = signUpOption.nextLine();
            data[counter++] = phno;
            System.out.print("Please Enter your Birth Date (DD-MM-YYYY): ");
            String dob = signUpOption.nextLine();
            data[counter++] = dob;
            System.out.print("Please Enter your Gender M/F/O: ");
            String gender = signUpOption.nextLine().toUpperCase();
            data[counter++] = gender;
            data[counter] = "patient";
            if(checkConstraints(fName, lName, email, password, confirmPassword,phno, dob, gender)){
//                msg=ANSI_GREEN+"Sucessfully Signed Up.!"+ANSI_RESET;
                WriteToExcel newUserRecord = new WriteToExcel();
//                String[] dataa = {"Pal","kotvir","pallavi@gmail.com","Asdf@123","14-081995","Female","9916067559"};
                try{
                newUserRecord.writeData(CommonVariables.userRecordFileName, CommonVariables.userRecordLabels, data, CommonVariables.signupClass );
                }catch(Exception e){
                }
//                System.out.println(msg);
                System.out.println("\n\nRedirecting to main menu....\n\n");
                signUpLoop = false;
            }
            else{
                Scanner option = new Scanner(System.in);
                System.out.println("Do you want to try again?");
                System.out.println("1. Yes");
                System.out.println("2. No");
                System.out.print("Please select any option from above list:");
                switch (option.nextInt()) {
                    case 1:
//                        data=new String[9];
                        break;
                    case 2:
                        signUpLoop = false;
                        break;
                    default:
                        System.out.println("Please enter valid input \n \n");
                        break;
                }
            }
        }
        HospitalManagementSystem mainMenuObj = new HospitalManagementSystem();
        mainMenuObj.mainMenu();    
    }
    boolean checkConstraints(String fName,String lName,String email, String password, String confirmPassword, String phno, String dob, String gender){
        boolean isValid = true;
        
        //Check Fname
        if(!(fName != null && !fName.trim().equals("") && fName.matches("[a-zA-Z]*"))){
            msg = "\n\n"+ANSI_RED+"Invalid First Name...! First Name can only contain alphabets. (Not even space) \n\n" + ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
        //Check Lname
        if(!(lName != null && !lName.trim().equals("") && lName.matches("[a-zA-Z]*"))){
            msg = "\n\n "+ANSI_RED+"Invalid Last Name...! Last Name can only contain alphabets. (Not even space) \n\n" + ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
        if(!(dob != null && validateJavaDate(dob))){
            msg = "\n\n" +ANSI_RED+" Invalid Date of Birth...! Birth date should be in formate: DD-MM-YYYY"+ ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
        if(!(password != null && !password.trim().equals("") && password.length() >= 8)){
            msg = "\n\n" +ANSI_RED+" Invalid Password...! Password should be atleast 8 charater long"+ ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
        if(!(password.equals(confirmPassword) )){
            msg = "\n\n" +ANSI_RED+" Confirm password did not match...!"+ ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
         if(!(phno != null && !phno.trim().equals("") && phno.matches("[0-9]*") && phno.length() == 10)){
            msg = "\n\n "+ANSI_RED+"Invalid Phone Number...! \n\n" + ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
         if(!(gender.equals("M") || gender.equals("F") || gender.equals("O"))){
            msg = "\n\n" +ANSI_RED+"Invalid Gender...! Gender should be M/F/O"+ ANSI_RESET;
            System.out.println(msg);
            isValid = false;
            return isValid;
        }
        return isValid;
    }
    
    
    public static boolean validateJavaDate(String strDate)
    {
         if (strDate.trim().equals(""))
         {
             return true;
         }
         else
         {
             SimpleDateFormat sdfrmt = new SimpleDateFormat("dd-MM-yyyy");
             sdfrmt.setLenient(false);
             try{
                 Date javaDate = sdfrmt.parse(strDate); 
             }
             catch (ParseException e)
             {
                 return false;
             }
             /* Return true if date format is valid */
             return true;
         }
    }
}

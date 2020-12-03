/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shared;

/**
 *
 * @author NIAR Tech
 */
public class CommonVariables {

    public static String secretKey = "SecretGroup123";
    public static String salt = "DontShare";
    public static String instanceName = "PBKDF2WithHmacSHA256";
    public static String directoryPath = "./data/";
    public static String[] userRecordLabels = {"fname", "lname", "email", "pwd", "DOB", "phoneNo", "gender", "UserType"};
    public static String userRecordFileName = "user_record.xls";
    public static String[] appointmentLabels = {"timeId", "username", "timing"};
    public static String appointmentLabelsFileName = "appointment_schedule.xls";
    public static String[] patientRecordsLabels = {"username", "Blood_Sugar", "Heart_Beat", "Enter_Weight", "Symptoms", "Prescription", "date&Time"};
    public static String patientRecordsFileName = "patient_records.xls";
    public static String signupClass = "signup";
    public static String nonSignupClass = "no";

}
